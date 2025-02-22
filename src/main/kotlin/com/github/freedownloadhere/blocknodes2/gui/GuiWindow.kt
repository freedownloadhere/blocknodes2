package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayout
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils

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
        LayoutUtils.scaleIn(this, LayoutUtils.Rectangle.wholeScreen, 0.9)
        LayoutUtils.heightScaling(this, h, arrayOf(0.1, 0.9))
        LayoutUtils.list(this, 0.0, 0.0)
        LayoutUtils.makeChildrenSameWidth(this, w)
        LayoutUtils.centerIn(this, LayoutUtils.Rectangle.wholeScreen)
        for(child in contents.children)
            LayoutUtils.scaleHeightTo(child, 0.1 * contents.h)
    }
}