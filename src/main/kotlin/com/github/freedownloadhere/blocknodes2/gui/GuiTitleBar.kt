package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPre
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiTitleBar(title : String)
    : Gui(), IDrawable, ILayoutPre, IParent
{
    override var baseColor = ColorHelper.GuiNeutralDark
    override val children = listOf(GuiText(title))

    override var applyLayoutPre = {
        val rect = LayoutUtils.Rectangle(this)
        LayoutUtils.scaleIn(children.first(), rect, 0.5)
        LayoutUtils.centerIn(children.first(), rect)
    }

    override fun draw() { Manager.renderer.drawBasicBG(this) }
}