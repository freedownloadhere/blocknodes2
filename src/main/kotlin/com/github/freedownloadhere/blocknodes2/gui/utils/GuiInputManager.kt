package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.utils.GuiManager.height
import org.lwjgl.input.Mouse

class GuiInputManager {
    var lastMouseX = -1
        private set
    var lastMouseY = -1
        private set
    var lastDwheel = 0
        private set
    val scrollSens = 10

    val mouseIsClicked : Boolean
        get() = Mouse.getEventButtonState()
    val mouseButtonMask : Int
        get() = Mouse.getEventButton()

    fun updateMouse() {
        lastMouseX = Mouse.getEventX()
        lastMouseY = height - Mouse.getEventY() - 1
        lastDwheel = Mouse.getEventDWheel()
    }
}