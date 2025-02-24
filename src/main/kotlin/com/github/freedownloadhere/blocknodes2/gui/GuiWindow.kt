package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayout
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils

class GuiWindow(title : String) : Gui(), IParent, ILayout {
    override val children = listOf<Gui>(
        GuiTitleBar(title),
        GuiListContainer()
    )

    private val titleBar : GuiTitleBar
        get() = children[0] as GuiTitleBar
    private val contents : GuiListContainer
        get() = children[1] as GuiListContainer

    init {
        for(i in 1..50)
            contents.addChild(GuiText("This is a sample text. $i"))
    }

    override fun applyLayout() {
        LayoutUtils.scaleIn(this, LayoutUtils.Rectangle.wholeScreen, 0.9)

        LayoutUtils.scaleHeightTo(titleBar, 0.1 * h)
        titleBar.w = w

        LayoutUtils.scaleHeightTo(contents, 0.9 * h)
        contents.w = w

        LayoutUtils.list(this, 0.0, 0.0)

        LayoutUtils.centerIn(this, LayoutUtils.Rectangle.wholeScreen)
    }
}