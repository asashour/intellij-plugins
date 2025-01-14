// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.terraform.config.model.local

import com.intellij.platform.workspace.storage.*
import com.intellij.platform.workspace.storage.EntitySource
import com.intellij.platform.workspace.storage.EntityType
import com.intellij.platform.workspace.storage.GeneratedCodeApiVersion
import com.intellij.platform.workspace.storage.MutableEntityStorage
import com.intellij.platform.workspace.storage.WorkspaceEntity
import com.intellij.platform.workspace.storage.url.VirtualFileUrl

interface TFLocalMetaEntity : WorkspaceEntity {

  val timeStampLow: Int

  val timeStampHigh: Int

  val jsonPath: String

  val lockFile: VirtualFileUrl

  val timeStamp: Long
    get() {
      return timeStampLow.toLong() and 0xFFFFFFFFL or (timeStampHigh.toLong() shl 32)
    }

  object LockEntitySource : EntitySource

  //region generated code
  @GeneratedCodeApiVersion(2)
  interface Builder : TFLocalMetaEntity, WorkspaceEntity.Builder<TFLocalMetaEntity> {
    override var entitySource: EntitySource
    override var timeStampLow: Int
    override var timeStampHigh: Int
    override var jsonPath: String
    override var lockFile: VirtualFileUrl
  }

  companion object : EntityType<TFLocalMetaEntity, Builder>() {
    @JvmOverloads
    @JvmStatic
    @JvmName("create")
    operator fun invoke(timeStampLow: Int,
                        timeStampHigh: Int,
                        jsonPath: String,
                        lockFile: VirtualFileUrl,
                        entitySource: EntitySource,
                        init: (Builder.() -> Unit)? = null): TFLocalMetaEntity {
      val builder = builder()
      builder.timeStampLow = timeStampLow
      builder.timeStampHigh = timeStampHigh
      builder.jsonPath = jsonPath
      builder.lockFile = lockFile
      builder.entitySource = entitySource
      init?.invoke(builder)
      return builder
    }
  }
  //endregion

}

//region generated code
fun MutableEntityStorage.modifyEntity(entity: TFLocalMetaEntity,
                                      modification: TFLocalMetaEntity.Builder.() -> Unit): TFLocalMetaEntity = modifyEntity(
  TFLocalMetaEntity.Builder::class.java, entity, modification)
//endregion
