// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.terraform.config.actions

import com.intellij.notification.NotificationType
import com.intellij.openapi.components.service
import com.intellij.openapi.components.serviceAsync
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import kotlinx.coroutines.CancellationException
import org.intellij.terraform.config.TerraformConstants
import org.intellij.terraform.config.model.local.LocalSchemaService
import org.intellij.terraform.hcl.HCLBundle

class TFGenerateLocalMetadataAction : TFExternalToolsAction() {

  override suspend fun invoke(project: Project, module: Module?, title: String, virtualFile: VirtualFile) {
    val localSchemaService = project.serviceAsync<LocalSchemaService>()
    val lockFile = localSchemaService.findLockFile(virtualFile)
    if (lockFile == null) {
      TerraformConstants.EXECUTION_NOTIFICATION_GROUP
        .createNotification(
          HCLBundle.message("notification.title.cant.generate.model"),
          HCLBundle.message("notification.content.there.no.terraform.lock.hcl.found.please.run.terraform.init"),
          NotificationType.ERROR
        ).notify(project)
      return
    }
    localSchemaService.clearLocalModel(lockFile)
    localSchemaService.scheduleModelRebuild(setOf(lockFile)).await()
  }

}