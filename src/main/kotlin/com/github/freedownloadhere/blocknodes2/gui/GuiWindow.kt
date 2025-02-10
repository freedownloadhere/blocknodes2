package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiWindow(str : String = "Window") : GuiList(0, 0) {
    private val topBar = GuiList()
    val contents = GuiList()

    init {
        flagList.add(Flags.ListHomogenousWidths)

        topBar.bgColor = ColorHelper.GuiNeutralDark
        topBar.flagList.add(Flags.ListStaticSize)
        topBar.addChild(GuiText(str))

        addChild(topBar)
        addChild(contents)
    }
}