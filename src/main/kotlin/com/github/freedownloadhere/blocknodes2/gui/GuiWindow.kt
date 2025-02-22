package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayout
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager

class GuiWindow(title : String) : Gui(), IParent, ILayout {
    override val children : List<Gui>
    private val contents : GuiListContainer
        get() = children[1] as GuiListContainer

    init {
        val titleBar = GuiTitleBar(title)
        val actualContents = GuiListContainer()

        for(i in 1..50)
            actualContents.addChild(GuiText("This is a sample text. $i"))
        children = listOf(titleBar, actualContents)
    }

    override fun applyLayout() {
        val baseW = Manager.width.toDouble()
        val baseH = Manager.height.toDouble()
        LayoutUtils.scaleInRectangle(this, 0.0, 0.0, baseW, baseH, 0.9)
        LayoutUtils.heightScaling(this, h, arrayOf(0.1, 0.9))
        LayoutUtils.list(this, 0.0, 0.0)
        LayoutUtils.makeChildrenSameWidth(this, w)
        LayoutUtils.centerInRectangle(this, 0.0, 0.0, baseW, baseH)
    }
}