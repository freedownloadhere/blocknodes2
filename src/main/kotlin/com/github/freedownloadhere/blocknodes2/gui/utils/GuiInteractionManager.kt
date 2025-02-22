package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.*

class GuiInteractionManager(private val inputManager: GuiInputManager, private val rootGui : Gui?) {
    var focused : Gui? = null
        private set
    private var hovered : Gui? = null
    private var lastMouseOn : Gui? = null

    fun handleMouseInput() {
        if(rootGui == null) return
        lastMouseOn = findMouseOn(rootGui)
        onHover()
        onMouseClick()
        onScroll()
    }

    fun handleKeyTyped(typedChar : Char, keyCode : Int) {
        if(focused != null && focused is IGuiTypable)
            (focused as IGuiTypable).onKeyTyped(typedChar, keyCode)
    }

    private fun onHover() {
        if(lastMouseOn != hovered) {
            if(hovered != null && hovered is IGuiHoverable)
                (hovered as IGuiHoverable).onHoverStop()
            if(lastMouseOn != null && lastMouseOn is IGuiHoverable)
                (lastMouseOn as IGuiHoverable).onHoverStart()
            hovered = lastMouseOn
        }
    }

    private fun onMouseClick() {
        if(inputManager.mouseIsClicked) {
            focused = lastMouseOn
            if(focused != null && focused is IGuiClickable)
                (focused as IGuiClickable).onClick(inputManager.mouseButtonMask)
        }
    }

    private fun onScroll() {
        if(inputManager.lastDwheel != 0) {
            val d = inputManager.lastDwheel / inputManager.scrollSens
            if(focused != null && focused is IGuiScrollable)
                (focused as IGuiScrollable).onScroll(d)
        }
    }

    private fun findMouseOn(u : Gui) : Gui? {
        if(!u.toggled)
            return null

        if(u is IGuiParent)
            for(v in u.children) {
                val gui = findMouseOn(v)
                if(gui != null)
                    return gui
            }

        val x = inputManager.lastMouseX
        val y = inputManager.lastMouseY
        if(u.x <= x && x <= u.x + u.w)
            if(u.y <= y && y <= u.y + u.h)
                if(u is IGuiInteractable)
                    return u

        return null
    }
}