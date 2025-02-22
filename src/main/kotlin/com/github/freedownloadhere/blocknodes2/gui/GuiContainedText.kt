package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParent
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiManager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiContainedText(text : String) : Gui(), IGuiDrawable, IGuiParent {
    val contained : Gui
        get() = children.first()
    override val children = listOf(GuiText(text))
    override var baseColor = ColorHelper.GuiNeutral
    override fun draw() {
        GuiManager.renderer.drawBasicBG(this)
    }
}