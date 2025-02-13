package com.github.freedownloadhere.blocknodes2.gui

class GuiScrollableList(width : Int, height : Int) : GuiCroppedContainer(width, height) {
    val contents = GuiList()
    private var displayY : Double
    private val originalY : Double

    init {
        addChild(contents)
        originalY = contents.y
        displayY = contents.y
        contents.flagList.add(Flags.ShouldNotInteract)
        contents.flagList.add(Flags.TransparentBG)
    }

    override fun onScroll(d: Int) {
        displayY += d
        displayY = displayY.coerceIn(0.0, contents.h - h)
        contents.setPosition(contents.x, displayY + 2 * vSpacing)
    }
}