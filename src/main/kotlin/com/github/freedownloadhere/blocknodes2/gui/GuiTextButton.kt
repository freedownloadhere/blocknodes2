package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPre
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils

class GuiTextButton(str : String, callback : () -> Unit) : GuiButton(callback), IParent, ILayoutPre {
    override val children = listOf(GuiText(str))

    private val textGui : GuiText
        get() = children[0]

    override fun applyLayoutPre() {
        LayoutUtils.stretchToFit(this, 1.0)
        LayoutUtils.centerIn(textGui, LayoutUtils.Rectangle(this))
    }
}