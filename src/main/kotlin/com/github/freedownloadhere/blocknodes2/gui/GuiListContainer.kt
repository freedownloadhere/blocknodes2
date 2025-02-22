package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiOrdered
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParentExtendable
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiLayout
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiManager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiListContainer : Gui(), IGuiDrawable, IGuiOrdered, IGuiParentExtendable {
    override var baseColor = ColorHelper.GuiNeutral
    override fun draw() { GuiManager.renderer.drawBasicBG(this) }
    override val children = mutableListOf<Gui>()
    override fun addChild(child: Gui) {
        children.add(child)
    }
    override fun applyOrdering() {
        GuiLayout.list(this, 10.0, 10.0)
    }
}