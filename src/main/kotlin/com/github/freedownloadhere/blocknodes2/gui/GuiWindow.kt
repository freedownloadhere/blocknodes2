package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

class GuiWindow(str : String = "Window") : GuiList(0, 0) {
    private val topBar = GuiList()
    val contents = GuiList()

    init {
        topBar.bgColor = ColorHelper.GuiNeutralDark
        topBar.addChild(GuiText(str))

        addChild(topBar)
        addChild(contents)
    }

    override fun update(deltaTime: Long) {
        super.update(deltaTime)
        topBar.w = contents.w
    }
}