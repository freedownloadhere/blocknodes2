package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.max

class GuiButton(
    x: Double,
    y: Double,
    w: Double,
    h: Double,
    private val callback : () -> Unit
) : GuiInteractable(x, y, w, h) {
    private lateinit var text : GuiText
    private var clickCooldown = 0L

    override fun finish() : Gui {
        text = GuiText(0.0, 0.0, w * 0.75, h * 0.75, "Button")
            .centerIn(this)
            .finish() as GuiText

        addChild(text)

        return this
    }

    override fun draw() {
        drawBorder()
        drawBG(
            if(GuiManager.hovered == this)
                ColorHelper.GuiBGLight
            else
                ColorHelper.GuiBGDark
        )
    }

    override fun onClick(button: Int) {
        if(button == 0 && clickCooldown == 0L) {
            callback()
            clickCooldown = 500L
        }
    }

    override fun update(deltaTime: Long) {
        clickCooldown = max(0L, clickCooldown - deltaTime)
        super.update(deltaTime)
    }
}