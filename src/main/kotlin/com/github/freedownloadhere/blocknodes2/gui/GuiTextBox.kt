package com.github.freedownloadhere.blocknodes2.gui

import org.lwjgl.input.Keyboard

class GuiTextBox : GuiInteractable() {
    var contents : String = " "
        private set
    private val guiText = GuiText(contents)

    override fun postInit() {
        super.postInit()
        guiText.scaleExpandIn(this)
        guiText.scale(0.8)
        guiText.translateCenterIn(this)
        guiText.translateSnapTo(this, SnapDir.Left, 5.0)
        addChild(guiText)
    }

    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
        if(contents.isNotEmpty() && keyCode == Keyboard.KEY_BACK || keyCode == Keyboard.KEY_DELETE)
            contents = contents.dropLast(1)

        else if(!typedChar.isISOControl())
            contents += typedChar

        guiText.updateText("$contents ")
    }
}