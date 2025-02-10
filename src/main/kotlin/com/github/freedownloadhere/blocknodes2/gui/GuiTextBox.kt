package com.github.freedownloadhere.blocknodes2.gui

import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11

class GuiTextBox : GuiList() {
    private var contents : String = "Your text here"
    private val guiText = GuiText(contents)

    init {
        addChild(guiText)
    }

    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
        if(contents.isNotEmpty() && keyCode == Keyboard.KEY_BACK || keyCode == Keyboard.KEY_DELETE)
            contents = contents.dropLast(1)

        else if(!typedChar.isISOControl())
            contents += typedChar

        guiText.updateText("$contents ")
    }

    override fun update(deltaTime: Long) {
        draw()
        GL11.glEnable(GL11.GL_SCISSOR_TEST)
        GL11.glScissor(x.toInt(), GuiManager.height - y.toInt() - h.toInt(), w.toInt(), h.toInt())
        guiText.update(deltaTime)
        GL11.glDisable(GL11.GL_SCISSOR_TEST)
    }
}