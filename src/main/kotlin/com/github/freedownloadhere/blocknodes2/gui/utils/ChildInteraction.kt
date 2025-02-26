package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.GuiListContainer

object ChildInteraction {
    fun <A : Gui, B : Gui> interaction(parent : A, child : B) { }

    fun interaction(parent : GuiListContainer, child : GuiListContainer) {
        LayoutUtils.setAspectRatio(child, 2.0)
        LayoutUtils.scaleIn(child, LayoutUtils.Rectangle(parent), Manager.config.listSpacingScale)
    }
}