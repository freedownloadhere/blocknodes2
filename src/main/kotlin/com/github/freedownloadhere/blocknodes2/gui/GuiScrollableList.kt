package com.github.freedownloadhere.blocknodes2.gui

import kotlin.math.max
import kotlin.math.min

class GuiScrollableList(width : Int, height : Int) : GuiCroppedContainer(width, height) {
    val contents = GuiList()
    private var displayY : Double = 0.0
    private var originY : Double = 0.0

    init {
        addChild(contents)
        contents.flagList.add(Flags.ShouldNotInteract)
        contents.flagList.add(Flags.TransparentBG)
    }

    fun setAlignment(origin : Double) {
        originY = origin
        displayY = origin
    }

    override fun onScroll(d: Int) {
        if(contents.h <= h)
            return
        displayY += d
        displayY = min(displayY, originY)
        displayY = max(displayY, originY - contents.h + h)
        contents.setPosition(contents.x, displayY)
    }
}