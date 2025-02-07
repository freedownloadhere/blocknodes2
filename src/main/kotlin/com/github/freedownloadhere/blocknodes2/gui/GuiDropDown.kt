package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiDropDown(
    x: Double, y: Double, w: Double, h: Double,
    text : String
) : GuiInteractable(x, y, w, h) {
    private val contents = GuiWindow(0.0, 0.0, w, h)
    private val title = GuiText(0.0, 0.0, w, h, text)
    private val button = GuiButton(0.0, 0.0, w, h, "Toggle") { contents.toggle() }

    init {
        addChild(contents)
        addChild(title)
        addChild(button)

        contents.moveBy(0.0, h)
        contents.scaleBy(1.0, 5.0)

        title.centerIn(this)
        title.snapTo(this, Direction.Left)
        title.scaleBy(0.5)

        button.setWH(h, h)
        button.scaleBy(0.75)
        button.centerIn(this)
        button.snapTo(this, Direction.Right)
    }

    override fun draw() {
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()

        drawBG(ColorHelper.GuiBGDarkest)
    }
}