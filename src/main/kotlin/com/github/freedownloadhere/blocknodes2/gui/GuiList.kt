package com.github.freedownloadhere.blocknodes2.gui

import kotlin.math.max

open class GuiList(
    vS : Int = 10,
    hS : Int = 10
) : GuiInteractable() {
    var vSpacing = vS
        private set
    var hSpacing = hS
        private set

    fun setSpacing(newVSpacing : Int, newHSpacing : Int) {
        vSpacing = newVSpacing
        hSpacing = newHSpacing
    }

    // dont like this
    // generics no work also?
    // find better approach

    fun newText(contents : String) : GuiText {
        val gui = GuiText(contents)
        addChild(gui)
        return gui
    }

    fun newTextBox(placeholder : String) : GuiTextBox {
        val gui = GuiTextBox(placeholder)
        addChild(gui)
        return gui
    }

    fun newButton(text : String, callback : () -> Unit) : GuiButton {
        val gui = GuiButton(text, callback)
        addChild(gui)
        return gui
    }

    fun newScrollableList(width : Int, height : Int) : GuiScrollableList {
        val gui = GuiScrollableList(width, height)
        addChild(gui)
        gui.updateAlignment()
        return gui
    }

    override fun addChild(child: Gui) : Gui {
        extendList(child)
        return super.addChild(child)
    }

    override fun update(deltaTime: Long) {
        dynamicResize()
        super.update(deltaTime)
    }

    private fun dynamicResize() {
        if(flagList.isActive(Flags.ListStaticSize))
            return

        if(flagList.isNotActive(Flags.ListStaticWidth))
            w = 0.0
        if(flagList.isNotActive(Flags.ListStaticHeight))
            h = 0.0
        
        for(child in children)
            extendList(child)

        if(flagList.isNotActive(Flags.ListStaticWidth))
        if(flagList.isActive(Flags.ListHomogenousWidths))
            for(child in children)
                child.w = w - 2 * vSpacing
    }

    private fun extendList(elem : Gui) {
        if(flagList.isNotActive(Flags.ListStaticHeight)) {
            h = max(0.0, h - hSpacing)
            elem.setPosition(x + vSpacing, y + h + hSpacing)
            h += elem.h + 2 * hSpacing
        }
        if(flagList.isNotActive(Flags.ListStaticWidth))
            w = max(w, elem.w + 2 * vSpacing)
    }
}