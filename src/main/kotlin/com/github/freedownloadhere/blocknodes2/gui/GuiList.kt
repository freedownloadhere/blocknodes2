package com.github.freedownloadhere.blocknodes2.gui

import kotlin.math.max

open class GuiList(
    private var vSpacing : Int = 10,
    private var hSpacing : Int = 10
) : GuiInteractable() {
    fun setSpacing(newVSpacing : Int, newHSpacing : Int) {
        vSpacing = newVSpacing
        hSpacing = newHSpacing
    }

    override fun addChild(child: Gui) : Gui {
        extendList(child)
        return super.addChild(child)
    }

    override fun update(deltaTime: Long) {
        updateSize()
        super.update(deltaTime)
    }

    private fun updateSize() {
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