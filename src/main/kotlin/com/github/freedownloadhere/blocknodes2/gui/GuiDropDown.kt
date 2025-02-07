package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiDropDown(
    x: Double, y: Double, w: Double, h: Double,
    text : String
) : GuiInteractable(x, y, w, h) {
    private val contents = GuiWindow(0.0, 0.0, w, h)
    private val title = GuiText(0.0, 0.0, w, h, text)
    private val button = GuiButton(0.0, 0.0, w, h, ":)") { contents.toggle() }

    init {
        contents.scaleBy(1.0, 5.0)
        addChild(contents)
        contents.moveBy(0.0, h)

        title.scaleBy(0.5)
        addChild(title)
        title.centerIn(this)
        title.snapTo(this, Direction.Left)

        button.setWH(h, h)
        button.scaleBy(0.75)
        addChild(button)
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