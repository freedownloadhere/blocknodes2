package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.*
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.max

open class GuiButton(private val callback: () -> Unit)
    : Gui(), IClickable, IHoverable, IDrawable
{
    private var clickCooldown = 0L

    override fun update(deltaTime: Long) {
        clickCooldown = max(0L, clickCooldown - deltaTime)
        super.update(deltaTime)
    }

    override var baseColor = ColorHelper.GuiNeutral

    override fun draw() {
        Manager.renderer.drawBasicBG(this)
    }

    override fun onClick(button: Int) {
        if(button == 0 && clickCooldown == 0L) {
            callback()
            clickCooldown = Manager.config.buttonClickCooldown
        }
    }

    override fun onHoverStart() { baseColor = ColorHelper.GuiNeutralLight }

    override fun onHoverStop() { baseColor = ColorHelper.GuiNeutral }
}