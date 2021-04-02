// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package training.lang

import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindowAnchor
import training.learn.exceptons.InvalidSdkException
import training.learn.exceptons.NoSdkException
import java.io.File

interface LangSupport {
  /** Is should be a language ID */
  val primaryLanguage: String
  val defaultProductName: String
    get() = ""
  val defaultProjectName: String
  val filename: String
    get() = "Learning"
  val langCourseFeedback: String?
    get() = null

  /** Callback should download and install demo project */
  val installRemoteProject: ((projectDirectory: File) -> Unit)?
    get() = null

  /** Relative path inside plugin resources. Used iff [installRemoteProject] is null*/
  val projectResourcePath: String
    get() = "/learnProjects/${primaryLanguage.toLowerCase()}/$defaultProjectName"

  /** Language can specify default sandbox-like file to be used for lessons with modifications but also with project support */
  val projectSandboxRelativePath: String?
    get() = null

  companion object {
    const val EP_NAME = "training.ift.language.extension"
  }

  /**
   * Implement that method to define SDK lookup depending on a given project.
   *
   * @return an SDK instance which (existing or newly created) should be applied to the project given. Return `null`
   * if no SDK is okay for this project.
   *
   * @throws NoSdkException in the case no valid SDK is available, yet it's required for the given project
   */
  @Throws(NoSdkException::class)
  fun getSdkForProject(project: Project): Sdk?

  fun applyProjectSdk(sdk: Sdk, project: Project)

  fun applyToProjectAfterConfigure(): (Project) -> Unit

  /**
   * <p> Implement that method to require some checks for the SDK in the learning project.
   * The easiest check is to check the presence and SdkType, but other checks like language level
   * might be applied as well.
   *
   * <p> The method should not return anything, but might throw exceptions subclassing InvalidSdkException which
   * will be handled by a UI.
   */
  @Throws(InvalidSdkException::class)
  fun checkSdk(sdk: Sdk?, project: Project)

  fun getProjectFilePath(projectName: String): String

  fun getToolWindowAnchor(): ToolWindowAnchor = ToolWindowAnchor.LEFT

  /** true means block source code modification in demo learning projects (scratches can be modified anyway)*/
  fun blockProjectFileModification(project: Project, file: VirtualFile): Boolean = false
}