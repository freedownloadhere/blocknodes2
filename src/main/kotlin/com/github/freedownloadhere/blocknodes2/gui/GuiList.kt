package com.github.freedownloadhere.blocknodes2.gui

import kotlin.math.max

open class GuiList(
    private val vSpacing : Int = 10,
    private val hSpacing : Int = 10
) : GuiInteractable() {
    override fun addChild(child: Gui) : Gui {
        extendList(child)
        return super.addChild(child)
    }

    override fun update(deltaTime: Long) {
        if(flagList.isNotActive(Flags.ListStaticSize))
            updateSize()
        super.update(deltaTime)
    }

    private fun updateSize() {
        w = 0.0
        h = 0.0
        for(child in children)
            extendList(child)

        if(flagList.isActive(Flags.ListHomogenousWidths))
            for(child in children)
                child.w = w - 2 * vSpacing
    }

    private fun extendList(elem : Gui) {
        h = max(0.0, h - hSpacing)
        elem.setPosition(x + vSpacing, y + h + hSpacing)
        h += elem.h + 2 * hSpacing
        w = max(w, elem.w + 2 * vSpacing)
    }
}