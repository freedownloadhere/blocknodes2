package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper

class GuiScrollableList(width : Int, height : Int) : GuiCroppedContainer(width, height) {
    val contents = GuiList()
    private var displayH = 0.0
    private val originalY : Double

    init {
        addChild(contents)
        originalY = contents.y
        contents.flagList.add(Flags.ShouldNotInteract)
        contents.flagList.add(Flags.TransparentBG)
    }

    override fun onScroll(d: Int) {
        ChatHelper.send("Scrolled for $d")
        displayH += d
        contents.setPosition(contents.x, originalY - displayH)
    }
}