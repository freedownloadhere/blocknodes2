package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParent

object GuiTranslation {
    fun setPosition(gui : Gui, newX : Double, newY : Double) {
        translate(gui, newX - gui.x, newY - gui.y)
    }

    private fun translate(gui : Gui, dx : Double, dy : Double) {
        gui.x += dx
        gui.y += dy
        if(gui is IGuiParent)
            for(child in gui.children)
                translate(child, dx, dy)
    }
}