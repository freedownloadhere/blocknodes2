package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiOrdered
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParent
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiLayout
import com.github.freedownloadhere.blocknodes2.gui.utils.GuiManager
import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiWindow(title : String) : Gui(), IGuiParent, IGuiOrdered {
    override val children : List<Gui>

    init {
        val titleBar = GuiContainedText(title)
        titleBar.baseColor = ColorHelper.GuiNeutralDark
        val actualContents = GuiListContainer()
        actualContents.addChild(GuiButton("wow") { ChatHelper.send("clicked") })
        children = listOf(titleBar, actualContents)
    }

    override fun applyOrdering() {
        val baseW = GuiManager.width.toDouble()
        val baseH = GuiManager.height.toDouble()
        GuiLayout.scaleInRectangle(this, 0.0, 0.0, baseW, baseH, 0.9)
        GuiLayout.heightScaling(this, h, arrayOf(0.1, 0.9))
        GuiLayout.list(this, 0.0, 0.0)
        GuiLayout.makeChildrenSameWidth(this, w)
    }
}