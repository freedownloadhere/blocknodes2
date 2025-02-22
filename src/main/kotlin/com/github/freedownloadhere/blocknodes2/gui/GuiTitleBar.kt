package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayout
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiTitleBar(title : String) : Gui(), IDrawable, ILayout, IParent {
    override var baseColor = ColorHelper.GuiNeutralDark
    override val children = listOf(GuiText(title))

    override fun draw() { Manager.renderer.drawBasicBG(this) }
    override fun applyLayout() {
        val rect = LayoutUtils.Rectangle(this)
        LayoutUtils.scaleIn(children.first(), rect, 0.5)
        LayoutUtils.centerIn(children.first(), rect)
    }
}