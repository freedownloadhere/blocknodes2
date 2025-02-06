package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiDropDown : GuiInteractable() {
    private val title = GuiText()
    private val button = GuiButton()

    override fun finish() : GuiDropDown {
        title
            .wh(100.0, 30.0)
            .snapTo(this, Direction.Left)
            .finish()

        button
            .xy(0.0, 0.0)
            .wh(30.0, 30.0)
            .snapTo(this, Direction.Right)
            .finish()

        addChild(title)
        addChild(button)

        return this
    }

    override fun draw() {
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()

        drawBG(ColorHelper.GuiBGDarkest)
    }
}