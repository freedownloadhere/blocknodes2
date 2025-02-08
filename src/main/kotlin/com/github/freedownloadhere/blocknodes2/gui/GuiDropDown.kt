package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiDropDown(text : String) : GuiInteractable() {
    lateinit var contents : GuiWindow
    private val title = GuiText(text)
    private val button = GuiButton("v") { contents.toggle() }

    override fun postInit() {
        if(!this::contents.isInitialized) {
            contents = GuiWindow()
            contents.scaleSetWH(w, h)
            contents.scale(1.0, 5.0)
            contents.translatePlaceRelativeTo(this)
            contents.translate(0.0, h + GuiManager.DefaultConfig.BORDER_THICKNESS * 2.0)
        }

        title.scaleExpandIn(this)
        title.scale(0.5)
        title.translateCenterIn(this)
        title.translateSnapTo(this, SnapDir.Left, 10.0)

        button.scaleExpandIn(this)
        button.scale(0.75)
        button.translateCenterIn(this)
        button.translateSnapTo(this, SnapDir.Right, 5.0)

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