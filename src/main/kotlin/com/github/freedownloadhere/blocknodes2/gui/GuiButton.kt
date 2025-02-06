package com.github.freedownloadhere.blocknodes2.gui

class GuiButton : GuiInteractable() {
    private val text = GuiText()

    init {
        addChild(text)
    }

    override fun wh(newW: Double, newH: Double): GuiInteractable {
        super.wh(newW, newH)
        text.wh(newW, newH)
        return this
    }

    override fun xy(newX: Double, newY: Double): GuiInteractable {
        super.xy(newX, newY)
        text.center(x + w / 2.0, y + h / 2.0)
        return this
    }

    override fun draw() {
        if(GuiManager.hovered == this)
            drawHL()
        else
            drawBorder()

        drawBG()
    }
}