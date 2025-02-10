package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.max

class GuiButton(
    str : String,
    private val callback : () -> Unit
) : GuiList() {
    private val displayedText = GuiText(str)
    private var clickCooldown = 0L

    init {
        addChild(displayedText)
    }

    override fun draw() {
        drawBorder()
        drawBG(
            if(GuiManager.hovered == this && clickCooldown == 0L)
                ColorHelper.GuiNeutralLight
            else
                ColorHelper.GuiNeutral
        )
    }

    override fun onClick(button: Int) {
        if(button == 0 && clickCooldown == 0L) {
            callback()
            clickCooldown = 100L
        }
    }

    override fun update(deltaTime: Long) {
        clickCooldown = max(0L, clickCooldown - deltaTime)
        super.update(deltaTime)
    }
}