package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiDropDown(text : String) : GuiInteractable() {
    private val contents = GuiWindow()
    private val title = GuiText(text)
    private val button = GuiButton("v") { contents.toggle() }

    override fun postInit() {
        contents.setWH(w, h)
        contents.scale(1.0, 5.0)
        contents.placeRelativeTo(this)
        contents.translate(0.0, h + GuiManager.DefaultConfig.BORDER_THICKNESS * 2.0)

        val testbutton = GuiButton("test") { ChatHelper.send("window button pressed") }
        testbutton.setWH(contents.w, contents.h)
        testbutton.scale(0.25)
        testbutton.placeRelativeTo(contents)

        val testbutton2 = GuiButton("longer text") { ChatHelper.send("big") }
        testbutton2.setWH(contents.w, contents.h)
        testbutton2.scale(0.5, 0.25)
        testbutton2.snapTo(contents, SnapDir.Bottom, 5.0)
        testbutton2.snapTo(contents, SnapDir.Right, 5.0)

        contents.addChild(testbutton)
        contents.addChild(testbutton2)

        title.expandIn(this)
        title.scale(0.5)
        title.centerIn(this)
        title.snapTo(this, SnapDir.Left, 10.0)

        button.expandIn(this)
        button.scale(0.75)
        button.centerIn(this)
        button.snapTo(this, SnapDir.Right, 5.0)

        addChild(contents)
        addChild(title)
        addChild(button)
    }

    override fun draw() {
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()

        drawBG(ColorHelper.GuiBGDarkest)
    }
}