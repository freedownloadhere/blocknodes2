package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiOrdered
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParent
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiLayout
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiManager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiTitleBar(title : String) : Gui(), IGuiDrawable, IGuiOrdered, IGuiParent {
    override var baseColor = ColorHelper.GuiNeutralDark
    override val children = listOf(GuiText(title))

    override fun draw() { GuiManager.renderer.drawBasicBG(this) }
    override fun applyOrdering() {
        GuiLayout.scaleInRectangle(children.first(), x, y, x + w, y + h, 0.5)
        GuiLayout.centerInRectangle(children.first(), x, y, x + w, y + h)
    }
}