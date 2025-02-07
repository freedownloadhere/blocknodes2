package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.max

class GuiButton(
    x: Double, y: Double, w: Double, h: Double,
    str : String,
    private val callback : () -> Unit,
) : GuiInteractable(x, y, w, h) {
    private val text = GuiText(0.0, 0.0, w, h, str)
    private var clickCooldown = 0L

    init {
        text.scaleBy(0.75)
        text.centerIn(this)
        addChild(text)
    }

    override fun draw() {
        drawBorder()
        drawBG(
            if(GuiManager.hovered == this && clickCooldown == 0L)
                ColorHelper.GuiBGLight
            else
                ColorHelper.GuiBGDark
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