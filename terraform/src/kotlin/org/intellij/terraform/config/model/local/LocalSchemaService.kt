// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.terraform.config.model.local

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.execution.ExecutionException
import com.intellij.execution.process.CapturingProcessAdapter
import com.intellij.openapi.application.readAction
import com.intellij.openapi.application.readAndWriteAction
import com.intellij.openapi.application.writeAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.getProjectDataPath
import com.intellij.openapi.util.NlsSafe
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.backend.workspace.WorkspaceModel
import com.intellij.platform.backend.workspace.toVirtualFileUrl
import com.intellij.platform.backend.workspace.virtualFile
import com.intellij.platform.ide.progress.withBackgroundProgress
import com.intellij.platform.util.coroutines.childScope
import com.intellij.platform.workspace.storage.entities
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.util.concurrency.annotations.RequiresBlockingContext
import com.intellij.util.containers.ContainerUtil
import kotlinx.coroutines.*
import org.intellij.terraform.config.TerraformFileType
import org.intellij.terraform.config.model.TypeModel
import org.intellij.terraform.config.model.TypeModelProvider
import org.intellij.terraform.config.model.getVFSParents
import org.intellij.terraform.config.model.loader.TerraformMetadataLoader
import org.intellij.terraform.config.util.TFExecutor
import org.intellij.terraform.config.util.executeSuspendable
import org.intellij.terraform.executeLatest
import org.intellij.terraform.hcl.HCLBundle
import org.intellij.terraform.hcl.HCLFileType
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.coroutines.coroutineContext
import kotlin.io.path.readText


const val TERRAFORM_LOCK_FILE_NAME: String = ".terraform.lock.hcl"

private const val DAEMON_RESTART_DEBOUNCE_TIMEOUT: Long = 300

private const val ORPHAN_COLLECTOR_DEBOUNCE_TIMEOUT: Long = 3000

@Service(Service.Level.PROJECT)
class LocalSchemaService(val project: Project, val scope: CoroutineScope) {

  private val modelBuildScope = scope.childScope()

  private val modelComputationCache = ContainerUtil.createConcurrentWeakMap<VirtualFile, Deferred<TypeModel?>>()

  @OptIn(ExperimentalCoroutinesApi::class)
  @RequiresBlockingContext
  fun getModel(virtualFile: VirtualFile): TypeModel? {
    val lock = findLockFile(virtualFile) ?: return null
    val myDeferred = modelComputationCache[lock]

    if (myDeferred == null || myDeferred.isCompleted && myDeferred.getCompletionExceptionOrNull() is CancellationException) {
      scheduleModelRebuild(setOf(lock))
      return null
    }

    if (!myDeferred.isCompleted) {
      return null
    }

    return try {
      myDeferred.getCompleted()
    }
    catch (e: Exception) {
      null
    }
  }

  suspend fun clearLocalModel(virtualFile: VirtualFile) {
    val lock = findLockFile(virtualFile) ?: return
    modelComputationCache.remove(lock)?.cancel()

    readAndWriteAction {
      val relatedEntities = WorkspaceModel.getInstance(project).currentSnapshot.entities<TFLocalMetaEntity>().filter {
        it.lockFile.virtualFile == lock
      }.toList()
      if (relatedEntities.isEmpty()) return@readAndWriteAction value(Unit)

      writeAction {
        WorkspaceModel.getInstance(project).updateProjectModel("remove related entities") { mutableEntityStorage ->
          for (relatedEntity in relatedEntities) {
            mutableEntityStorage.removeEntity(relatedEntity)
          }
        }
      }
    }
  }

  fun findLockFile(file: VirtualFile): VirtualFile? {
    if (file.name == TERRAFORM_LOCK_FILE_NAME) return file
    return getVFSParents(file, project).filter { it.isDirectory }.firstNotNullOfOrNull {
      it.findChild(TERRAFORM_LOCK_FILE_NAME)
    }
  }

  private val daemonRestarter: suspend () -> Unit = executeLatest {
    delay(DAEMON_RESTART_DEBOUNCE_TIMEOUT)
    awaitModelsReady()
    readAction {
      val openTerraformFiles = getOpenTerraformFiles()
      logger<LocalSchemaService>().info("openTerraformFiles to restart: $openTerraformFiles")
      for (openTerraformFile in openTerraformFiles) {
        DaemonCodeAnalyzer.getInstance(openTerraformFile.project).restart(openTerraformFile)
      }
    }
  }

  private fun getOpenTerraformFiles(): Set<PsiFile> {
    val fileTypeManager = FileTypeManager.getInstance()
    val fileTypes = listOf(TerraformFileType, HCLFileType)
    return ProjectManager.getInstance().openProjects.asSequence().flatMap { project ->
      FileEditorManager.getInstance(project).openFiles.asSequence()
        .filter { virtualFile -> fileTypes.any { fileTypeManager.isFileOfType(virtualFile, it) } }
        .mapNotNull { openFile -> PsiManager.getInstance(project).findFile(openFile) }
    }.toSet()
  }

  fun scheduleModelRebuild(locks: Set<VirtualFile>): Deferred<*> {
    val scheduled = mutableListOf<Deferred<*>>()
    for (lock in locks.mapNotNull { findLockFile(it) }) {
      modelComputationCache[lock]?.cancel()
      buildModel(lock).also {
        modelComputationCache[lock] = it
        scheduled.add(it)
      }
    }
    if (locks.isNotEmpty()) {
      scope.launch { daemonRestarter() }
    }
    return scope.async {
      scheduled.awaitAll()
    }
  }

  suspend fun awaitModelsReady() {
    modelBuildScope.coroutineContext.job.children.forEach { it.join() }
  }

  private fun buildModel(lock: VirtualFile): Deferred<TypeModel?> {
    return modelBuildScope.async {
      withBackgroundProgress(project, HCLBundle.message("rebuilding.local.schema"), false) {
        logger<LocalSchemaService>().info("building local model: $lock")
        val json = retrieveJsonForTFLock(lock)
        buildModelFromJson(json)
      }
    }
  }

  private suspend fun retrieveJsonForTFLock(lock: VirtualFile): String {
    val lockData = readAction {
      WorkspaceModel.getInstance(project).currentSnapshot.entities<TFLocalMetaEntity>().firstOrNull {
        it.lockFile.virtualFile == lock
      }.also {
        logger<LocalSchemaService>().info("building local model lockData: ${it?.lockFile?.virtualFile?.name} among ${
          WorkspaceModel.getInstance(project).currentSnapshot.entities<TFLocalMetaEntity>().count()
        }")
      }
    }

    if (lockData != null && lockData.timeStamp >= lock.timeStamp) {
      try {
        return readLockDataJsonFile(lockData)
      }
      catch (e: Exception) {
        if (e is CancellationException) throw e
        logger<LocalSchemaService>().warn("Cannot load model json: $lock", e)
      }
    }

    val generateResult = runCatching { generateNewJsonFile(lock) }
    if (generateResult.isFailure) {
      logger<LocalSchemaService>().info(
        "failed to generate new model for lock: ${lock.name}",
        generateResult.exceptionOrNull()
      )
    }

    val jsonFilePath: String = generateResult.getOrNull() ?: lockData?.let { ld ->
      try {
        readLockDataJsonFile(ld)
        logger<LocalSchemaService>().info("using previous logData for: ${lock.name}")
        ld.jsonPath
      }
      catch (lockDataException: Exception) {
        logger<LocalSchemaService>().info("failed to load previous lock data: ${lock.name}", lockDataException)
        val generateException = generateResult.exceptionOrNull()
        if (generateException != null) {
          generateException.addSuppressed(lockDataException)
          throw generateException
        }
        else {
          throw lockDataException
        }
      }
    } ?: generateResult.getOrThrow()

    writeAction {
      updateWorkspaceModel(lock, lockData, jsonFilePath)
    }
    return withContext(Dispatchers.IO) {
      localModelPath.resolve(jsonFilePath).readText()
    }
  }

  private suspend fun readLockDataJsonFile(lockData: TFLocalMetaEntity): String {
    return withContext(Dispatchers.IO) {
      localModelPath.resolve(lockData.jsonPath).readText()
    }
  }

  val localModelPath: Path
    get() {
      val localModelsPath = project.getProjectDataPath("terraform-local-models")
      Files.createDirectories(localModelsPath)
      return localModelsPath
    }

  private suspend fun generateNewJsonFile(lock: VirtualFile): @NlsSafe String {
    val jsonFromProcess = buildJsonFromTerraformProcess(project, lock)
    return withContext(Dispatchers.IO) {
      val uuid = UUID.randomUUID().toString()
      val jsonFile = localModelPath.resolve("$uuid.json")
      Files.writeString(jsonFile, jsonFromProcess)
      scope.launch { orphanCollector() }
      localModelPath.relativize(jsonFile).toString()
    }
  }


  private val orphanCollector: suspend () -> Unit = executeLatest {
    delay(ORPHAN_COLLECTOR_DEBOUNCE_TIMEOUT)
    awaitModelsReady()
    withBackgroundProgress(project, HCLBundle.message("progress.title.removing.unused.metadata")) {
      val localModelPath = localModelPath
      val allModelFiles = withContext(Dispatchers.IO) {
        Files.list(localModelPath).use { paths -> paths.map { localModelPath.relativize(it) }.toList() }
      }

      val usedMeta = readAction {
        WorkspaceModel.getInstance(project).currentSnapshot.entities<TFLocalMetaEntity>().mapTo(mutableSetOf()) { it.jsonPath }
      }

      logger<LocalSchemaService>().info("OrphanMetadataCollection: $localModelPath allModelFiles = $allModelFiles, usedMeta = $usedMeta")

      withContext(Dispatchers.IO) {
        for (file in allModelFiles) {
          if (file.toString() !in usedMeta) {
            Files.deleteIfExists(localModelPath.resolve(file))
          }
        }
      }
    }
  }

  private fun updateWorkspaceModel(lock: VirtualFile, prevLockData: TFLocalMetaEntity?, newJson: @NlsSafe String) {
    val low = (lock.timeStamp and 0xFFFFFFFFL).toInt()
    val high = (lock.timeStamp shr 32).toInt()
    val workspaceModel = WorkspaceModel.getInstance(project)
    workspaceModel.updateProjectModel("Update TF Local Model from $lock") { storage ->
      if (prevLockData != null)
        storage.removeEntity(prevLockData)
      storage.addEntity(TFLocalMetaEntity(low, high, newJson,
                                          lock.toVirtualFileUrl(workspaceModel.getVirtualFileUrlManager()),
                                          TFLocalMetaEntity.LockEntitySource

      ))
    }
  }

  private fun buildModelFromJson(json: String): TypeModel {
    val loader = TerraformMetadataLoader()
    json.byteInputStream().use { input ->
      loader.loadOne("local-schema.json", input)
    }
    loader.loadFrom(TypeModelProvider.globalModel)
    return loader.buildModel()
  }

  private suspend fun buildJsonFromTerraformProcess(project: Project, lock: VirtualFile): @NlsSafe String {
    logger<LocalSchemaService>().info("building local model buildJsonFromTerraformProcess: $lock")
    val capturingProcessAdapter = CapturingProcessAdapter()

    val success = TFExecutor.`in`(project, null)
      .withPresentableName(HCLBundle.message("rebuilding.local.schema"))
      .withParameters("providers", "schema", "-json")
      .withWorkDirectory(lock.parent.path)
      .withPassParentEnvironment(true)
      //.showOutputOnError()
      .withProcessListener(capturingProcessAdapter)
      .executeSuspendable()

    logger<LocalSchemaService>().info(
      "building local model buildJsonFromTerraformProcess result: ${coroutineContext.isActive}, $success  $lock")
    coroutineContext.ensureActive()

    val stdout = capturingProcessAdapter.output.stdout
    if (!success || stdout.isEmpty()) {
      logger<LocalSchemaService>().warn("failed tp build model for $lock")
      throw ExecutionException(HCLBundle.message("dialog.message.failed.to.get.output.terraform.providers.command.for", lock))
    }

    return stdout
  }

}


