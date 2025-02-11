package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11

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
        draw()
        GL11.glEnable(GL11.GL_SCISSOR_TEST)
        GL11.glScissor(x.toInt(), GuiManager.height - y.toInt() - h.toInt(), w.toInt(), h.toInt())
        guiText.update(deltaTime)
        GL11.glDisable(GL11.GL_SCISSOR_TEST)
    }
}