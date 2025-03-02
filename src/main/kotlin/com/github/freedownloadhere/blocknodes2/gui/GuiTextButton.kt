package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPost
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils

class GuiTextButton(str : String, callback : () -> Unit)
    : GuiButton(callback), IParent, ILayoutPost
{
    override val children = listOf(GuiText(str))

    private val textGui : GuiText
        get() = children[0]

    override fun applyLayoutPost() {
        LayoutUtils.stretchToFit(this)
        LayoutUtils.centerIn(textGui, LayoutUtils.Rectangle(this))
    }
}