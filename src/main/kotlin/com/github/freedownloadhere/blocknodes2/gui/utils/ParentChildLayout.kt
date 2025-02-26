package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.GuiListContainer

object ParentChildLayout {
    fun <A : Gui, B : Gui> apply(parent : A, child : B) { }

    fun apply(parent : GuiListContainer, child : GuiListContainer) {
        LayoutUtils.setAspectRatio(child, 2.0)
        LayoutUtils.scaleIn(child, LayoutUtils.Rectangle(parent), Manager.config.listSpacingScale)
    }
}