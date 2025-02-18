package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiWindow(x : Int, y : Int, str : String = "Window") : GuiList(0, 0) {
    private val topBar = GuiList()
    val contents = GuiList()

    init {
        flagList.add(Flags.ListHomogenousWidths)

        topBar.bgColor = ColorHelper.GuiNeutralDark
        topBar.newText(str)
        topBar.flagList.add(Flags.ListStaticSize)

        this.x = x.toDouble()
        this.y = y.toDouble()

        addChild(topBar)
        addChild(contents)
    }
}