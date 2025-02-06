package com.github.freedownloadhere.blocknodes2.gui

class GuiButton(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    private val text : String
) : GuiBase(x, y, width, height) {
    override fun draw() {
        if(GuiManager.hovered == this)
            drawHL()
        else
            drawBorder()

        drawBG()
        drawText(text)
    }
}