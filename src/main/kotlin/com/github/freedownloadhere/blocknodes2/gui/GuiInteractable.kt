package com.github.freedownloadhere.blocknodes2.gui

abstract class GuiInteractable : Gui() {
    private val children = mutableListOf<Gui>()

    override fun postInit() { }

    fun addChild(child : Gui) {
        if(!child.initialized)
            child.postInit()
        children.add(child)
    }

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

    override fun toggle() {
        super.toggle()
        for(child in children)
            child.toggle()
    }

    override fun translate(dx: Double, dy: Double) {
        super.translate(dx, dy)
        for(child in children)
            child.translate(dx, dy)
    }

    override fun scale(wMult : Double, hMult : Double) {
        super.scale(wMult, hMult)
        for(child in children)
            child.scale(wMult, hMult)
    }

    override fun update(deltaTime : Long) {
        super.update(deltaTime)
        for(child in children)
            child.update(deltaTime)
    }
}