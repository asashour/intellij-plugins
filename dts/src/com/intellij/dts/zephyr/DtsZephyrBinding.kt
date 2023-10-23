package com.intellij.dts.zephyr

import com.intellij.openapi.util.NlsSafe

data class DtsZephyrBinding(
    val compatible: @NlsSafe String?,
    val path: @NlsSafe String?,
    val description: @NlsSafe String?,
    val properties: Map<String, DtsZephyrPropertyBinding>,
    val child: DtsZephyrBinding?,
    val isChild: Boolean,
    val isBundled: Boolean,
) {
    companion object{
        val empty = Builder(null).build()
    }

    class Builder(
        private val compatible: String?,
        private val isChild: Boolean = false,
        private val isBundled: Boolean = false,
    ) {
        private var path: String? = null
        private var description: String? = null
        private var properties: MutableMap<String, DtsZephyrPropertyBinding.Builder> = mutableMapOf()
        private var child: Builder? = null

        fun setPath(value: String): Builder {
            if (path == null) path = value
            return this
        }

        fun setDescription(value: String): Builder {
            if (description == null) description = value
            return this
        }

        fun getPropertyBuilder(name: String): DtsZephyrPropertyBinding.Builder {
           return properties.getOrPut(name) { DtsZephyrPropertyBinding.Builder(name) }
        }

        fun getChildBuilder(): Builder {
            child?.let { return it }
            return Builder(compatible, isChild = true).also { child = it }
        }

        fun build(): DtsZephyrBinding = DtsZephyrBinding(
            compatible,
            path,
            description,
            properties.mapValues { (_, builder) -> builder.build() },
            child?.build(),
            isChild,
            isBundled,
        )
    }
}

data class DtsZephyrPropertyBinding(
    val name: @NlsSafe String,
    val description: @NlsSafe String?,
    val type: DtsZephyrPropertyType,
) {
    class Builder(private val name: String) {
        private var description: String? = null
        private var type: DtsZephyrPropertyType? = null

        fun setDescription(value: String): Builder {
            if (description == null) description = value
            return this
        }

        fun setType(value: String): Builder {
            if (type == null) type = DtsZephyrPropertyType.fromZephyr(value)
            return this
        }

        fun build(): DtsZephyrPropertyBinding = DtsZephyrPropertyBinding(
            name,
            description,
            type ?: DtsZephyrPropertyType.Compound,
        )
    }
}