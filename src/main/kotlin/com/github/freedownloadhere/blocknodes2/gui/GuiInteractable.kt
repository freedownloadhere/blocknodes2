package com.github.freedownloadhere.blocknodes2.gui

abstract class GuiInteractable(x: Double, y: Double, w: Double, h: Double) : Gui(x, y, w, h) {
    private val children = mutableListOf<Gui>()

    fun addChild(child : Gui) : Gui {
        child.moveBy(x, y)
        children.add(child)
        return child
    }

    fun getMouseOn(mouseX : Double, mouseY : Double) : GuiInteractable? {
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

    open fun onClick(button : Int) {
    }

    open fun onHover() {
    }

    override fun toggle() {
        super.toggle()
        for(child in children)
            child.toggle()
    }

    override fun moveBy(dx: Double, dy: Double) {
        super.moveBy(dx, dy)
        for(child in children)
            child.moveBy(dx, dy)
    }

    override fun scaleBy(wMult : Double, hMult : Double) {
        super.scaleBy(wMult, hMult)
        for(child in children)
            child.scaleBy(wMult, hMult)
    }

    override fun update(deltaTime : Long) {
        super.update(deltaTime)
        for(child in children)
            child.update(deltaTime)
    }
}