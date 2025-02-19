package com.github.freedownloadhere.blocknodes2.gui

import kotlin.math.max
import kotlin.math.min

class GuiScrollableList(width : Int, height : Int) : GuiCroppedContainer(width, height) {
    val contents = GuiList()
    private var movedY : Double = 0.0

    init {
        addChild(contents)
        contents.flagList.add(Flags.ShouldNotInteract)
        contents.flagList.add(Flags.TransparentBG)
    }

    fun updateAlignment() {
        movedY = y
    }

    override fun onScroll(d: Int) {
        if(contents.h <= h)
            return
        movedY += d
        movedY = min(movedY, y)
        movedY = max(movedY, y - contents.h + h)
        contents.setPosition(contents.x, movedY)
    }

    override fun update(deltaTime: Long) {
        for(child in contents.children) {
            if(child.y + child.h < y || child.y > y + h)
                child.disable()
            else
                child.enable()
        }
        super.update(deltaTime)
    }
}