package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import com.github.freedownloadhere.blocknodes2.util.ScissorStack
import org.lwjgl.input.Keyboard

class GuiTextBox(private val placeholder : String = "Type something...") : GuiList() {
    var contents : String = ""
        private set
    private val guiText = GuiText(placeholder)

    init {
        guiText.textCol = ColorHelper.GuiNeutralLight
        bgColor = ColorHelper.GuiNeutralDark
        addChild(guiText)
    }

    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
        if(contents.isNotEmpty() && keyCode == Keyboard.KEY_BACK || keyCode == Keyboard.KEY_DELETE)
            contents = contents.dropLast(1)

        else if(!typedChar.isISOControl())
            contents += typedChar

        if(contents.isEmpty()) {
            guiText.textCol = ColorHelper.GuiNeutralLight
            guiText.updateText("$placeholder ")
        }
        else {
            guiText.textCol = ColorHelper.White
            guiText.updateText("$contents ")
        }
    }

    override fun update(deltaTime: Long) {
        ScissorStack.push(this)
        ScissorStack.apply()
        super.update(deltaTime)
        ScissorStack.pop()
    }
}