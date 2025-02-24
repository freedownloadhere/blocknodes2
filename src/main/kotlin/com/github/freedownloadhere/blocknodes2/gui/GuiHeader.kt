package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPre
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager

class GuiHeader(str : String, private val parent : Gui)
    : GuiText("\u00A7l$str"), ILayoutPre
{
    override fun applyLayoutPre() {
        updateText(str)
        LayoutUtils.scale(this, Manager.config.textScale * 2.0)
    }
}