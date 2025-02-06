package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiButton : GuiInteractable() {
    private val text = GuiText()

    fun text(s : String) : GuiButton {
        text.text(s)
        return this
    }

    override fun finish() : GuiButton {
        text
            .wh(w * 0.75, h * 0.75)
            .centerIn(this)

        addChild(text)

        return this
    }

    override fun draw() {
        drawBorder()
        drawBG(
            if(GuiManager.hovered == this)
                ColorHelper.GuiBGLight
            else
                ColorHelper.GuiBGDark
        )
    }
}