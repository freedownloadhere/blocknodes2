package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.*

class InteractionManager(private val inputManager: InputManager, private val rootGui : Gui?) {
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
        if(focused != null && focused is ITypable)
            (focused as ITypable).onKeyTyped(typedChar, keyCode)
    }

    private fun onHover() {
        if(lastMouseOn != hovered) {
            if(hovered != null && hovered is IHoverable)
                (hovered as IHoverable).onHoverStop()
            if(lastMouseOn != null && lastMouseOn is IHoverable)
                (lastMouseOn as IHoverable).onHoverStart()
            hovered = lastMouseOn
        }
    }

    private fun onMouseClick() {
        if(inputManager.mouseIsClicked) {
            focused = lastMouseOn
            if(focused != null && focused is IClickable)
                (focused as IClickable).onClick(inputManager.mouseButtonMask)
        }
    }

    private fun onScroll() {
        if(inputManager.lastDwheel != 0) {
            val d = inputManager.lastDwheel / inputManager.scrollSens
            if(focused != null && focused is IScrollable)
                (focused as IScrollable).onScroll(d)
        }
    }

    private fun findMouseOn(u : Gui) : Gui? {
        if(!u.toggled)
            return null

        if(u is IParent)
            for(v in u.children) {
                val gui = findMouseOn(v)
                if(gui != null)
                    return gui
            }

        val x = inputManager.lastMouseX
        val y = inputManager.lastMouseY
        if(u.x <= x && x <= u.x + u.w)
            if(u.y <= y && y <= u.y + u.h)
                if(u is IInteractable)
                    return u

        return null
    }
}