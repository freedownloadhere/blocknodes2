package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiContainedText(text : String) : Gui(), IDrawable, IParent {
    val contained : Gui
        get() = children.first()
    override val children = listOf(GuiText(text))
    override var baseColor = ColorHelper.GuiNeutral
    override fun draw() {
        Manager.renderer.drawBasicBG(this)
    }
}