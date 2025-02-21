package com.github.freedownloadhere.blocknodes2.gui

import kotlin.math.max

open class GuiList(
    private val xSmult : Double = 0.3,
    private val ySmult : Double = 0.3,
) : GuiInteractable() {
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
    }

    private fun extendList(elem : Gui) {
        val xSpacing = w * xSmult
        val ySpacing = h * ySmult
        if(flagList.isNotActive(Flags.ListStaticHeight)) {
            h = max(0.0, h - ySpacing)
            elem.setPosition(x + ySpacing, y + h + ySpacing)
            h += elem.h + 2 * ySpacing
        }
        if(flagList.isNotActive(Flags.ListStaticWidth))
            w = max(w, elem.w + 2 * xSpacing)
    }
}