package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.min

class GuiDropDown(
    x: Double, y: Double, w: Double, h: Double,
    text : String
) : GuiInteractable(x, y, w, h) {
    private val contents = GuiWindow(0.0, 0.0, w, h)
    private val title = GuiText(0.0, 0.0, text)
    private val button = GuiButton(0.0, 0.0, h, h, "v") { contents.toggle() }

    init {
        contents.scaleBy(1.0, 5.0)
        addChild(contents)
        contents.moveBy(0.0, h + GuiManager.DefaultConfig.BORDER_THICKNESS)
        contents.addChild(GuiButton(10.0, 10.0, 100.0, 50.0, "test") { ChatHelper.send("window button pressed") })
        contents.addChild(GuiButton(10.0, 100.0, 100.0, 50.0, "longer text") { ChatHelper.send("big") })

        title.fill(this)
        title.scaleBy(0.5)
        addChild(title)
        title.centerIn(this)
        title.snapTo(this, Direction.Left, 10.0)

        //button.setWH(h, h)
        button.scaleBy(0.75)
        addChild(button)
        button.centerIn(this)
        button.snapTo(this, Direction.Right, 5.0)
    }

    override fun scaleBy(wMult: Double, hMult: Double) {
        scaleBy(min(wMult, hMult))
    }

    override fun draw() {
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()

        drawBG(ColorHelper.GuiBGDarkest)
    }
}