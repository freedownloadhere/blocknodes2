package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPre
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils

class GuiWindow(title : String)
    : Gui(), IParent, ILayoutPre
{
    override val children = listOf<Gui>(
        GuiTitleBar(title),
        GuiListContainer()
    )

    private val titleBar : GuiTitleBar
        get() = children[0] as GuiTitleBar
    val contents : GuiListContainer
        get() = children[1] as GuiListContainer

    override val applyLayoutPre = {
        LayoutUtils.scaleIn(this, LayoutUtils.Rectangle.wholeScreen, 0.9)

        LayoutUtils.scaleHeightTo(titleBar, 0.1 * h)
        titleBar.w = w

        LayoutUtils.scaleHeightTo(contents, 0.9 * h)
        contents.w = w

        LayoutUtils.list(this, 0.0, 0.0)

        LayoutUtils.centerIn(this, LayoutUtils.Rectangle.wholeScreen)
    }
}