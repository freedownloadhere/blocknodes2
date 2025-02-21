package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiWindow(x : Int, y : Int, str : String = "Window") : GuiList(0, 0) {
    private val topBar = GuiList()
    val contents : GuiScrollableList

    init {
        flagList.add(Flags.ListAllEqualWidths)

        topBar.bgColor = ColorHelper.GuiNeutralDark
        topBar.newText(str)
        topBar.flagList.add(Flags.ListStaticSize)
        addChild(topBar)

        this.x = x.toDouble()
        this.y = y.toDouble()

        contents = newScrollableList(1, GuiManager.height / 3)
        contents.flagList.add(Flags.ListAllEqualWidths)
    }
}