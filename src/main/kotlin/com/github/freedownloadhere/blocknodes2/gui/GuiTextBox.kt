package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import com.github.freedownloadhere.blocknodes2.util.ScissorStack
import org.lwjgl.input.Keyboard
import kotlin.reflect.KMutableProperty0

class GuiTextBox(
    private val contents : KMutableProperty0<String>,
    private val placeholder : String = "Type something..."
) : GuiList() {
    private val guiText = GuiText(placeholder)

    init {
        guiText.textCol = ColorHelper.GuiNeutralLight
        bgColor = ColorHelper.GuiNeutralDark
        addChild(guiText)
    }

    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
        if(contents.get().isNotEmpty() && keyCode == Keyboard.KEY_BACK || keyCode == Keyboard.KEY_DELETE)
            contents.set(contents.get().dropLast(1))

        else if(!typedChar.isISOControl())
            contents.set(contents.get() + typedChar)

        if(contents.get().isEmpty()) {
            guiText.textCol = ColorHelper.GuiNeutralLight
            guiText.updateText("$placeholder ")
        }
        else {
            guiText.textCol = ColorHelper.White
            guiText.updateText("${contents.get()} ")
        }
    }

    override fun update(deltaTime: Long) {
        ScissorStack.push(this)
        ScissorStack.apply()
        super.update(deltaTime)
        ScissorStack.pop()
    }
}