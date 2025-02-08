package com.github.freedownloadhere.blocknodes2.gui

abstract class GuiInteractable : Gui() {
    fun getMouseOn(mouseX : Double, mouseY : Double) : GuiInteractable? {
        if(!toggled)
            return null

        for(child in children) {
            if(child is GuiInteractable) {
                val gui = child.getMouseOn(mouseX, mouseY)
                if(gui != null)
                    return gui
            }
        }

        if(x <= mouseX && mouseX <= x + w)
            if(y <= mouseY && mouseY <= y + h)
                return this

        return null
    }

    open fun onClick(button : Int) { }

    open fun onHover() { }
}