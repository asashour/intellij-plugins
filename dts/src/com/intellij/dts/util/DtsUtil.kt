package com.intellij.dts.util

import com.intellij.dts.lang.DtsTokenSets
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.elementType
import com.intellij.psi.util.siblings

object DtsUtil {
    /**
     * Splits the name of a node into node and unit address part. If the name
     * does not contain a unit address null will be returned. If the name
     * contains multiple @ the last one will be used as the separator.
     */
    fun splitName(name: String): Pair<String, String?> {
        return if (name.contains("@")) {
            val splitIndex = name.indexOfLast { it == '@' }

            val actualName = name.substring(0, splitIndex)
            val unitAddress = name.substring(splitIndex + 1)

            Pair(actualName, unitAddress)
        } else {
            Pair(name, null)
        }
    }

    /**
     * Iterates over the children of a psi element but skips comments and
     * preprocessor statements.
     */
    fun children(element: PsiElement, forward: Boolean = true): Sequence<PsiElement> {
        val start = if (forward) element.firstChild else element.lastChild

        return start.siblings(forward = forward).filter {
            val type = it.elementType

            if (type == TokenType.WHITE_SPACE) return@filter false
            if (type in DtsTokenSets.comments) return@filter false

            // filter preprocessor statements and includes

            true
        }
    }
}