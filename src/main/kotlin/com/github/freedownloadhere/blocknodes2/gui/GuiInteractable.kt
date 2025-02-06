package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.GuiManager.hovered
import com.github.freedownloadhere.blocknodes2.util.ChatHelper

abstract class GuiInteractable : Gui() {
    protected val children = mutableListOf<Gui>()

    fun addChild(child : Gui) {
        child.xy(x + child.x, y + child.y)
        children.add(child)
    }

    fun getMouseOn(mouseX : Double, mouseY : Double) : GuiInteractable? {
        if(!(x <= mouseX && mouseX <= x + w))
            return null

        if(!(y <= mouseY && mouseY <= y + h))
            return null

        var clickedGui : GuiInteractable? = this

        for(child in children) {
            if(child is GuiInteractable) {
                val gui = child.getMouseOn(mouseX, mouseY)
                if(gui != null) {
                    clickedGui = gui
                    break
                }
            }
        }

        return clickedGui
    }

    fun onClick(button : Int) {
    }

    fun onHover() {
    }

    override fun xy(newX: Double, newY: Double): Gui {
        super.xy(newX, newY)
        for(child in children)
            child.xy(child.x + newX, child.y + newY)
        return this
    }

    override fun update() {
        super.update()
        for(child in children)
            child.update()
    }
}