package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiDropDown(
    x: Double,
    y: Double,
    w: Double,
    h: Double,
    private val text : String
) : GuiInteractable(x, y, w, h) {
    private lateinit var title : GuiText
    private lateinit var button: GuiButton

    override fun finish() : Gui {
        title = GuiText(0.0, 0.0, w / 2.0, h, text)
            .snapTo(this, Direction.Left)
            .finish() as GuiText

        button = GuiButton(0.0, 0.0, w / 2.0, h)
            { ChatHelper.send("Pressed Dropdown Button") }
            .snapTo(this, Direction.Right)
            .finish() as GuiButton

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