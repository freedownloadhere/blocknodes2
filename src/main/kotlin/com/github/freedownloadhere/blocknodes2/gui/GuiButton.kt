package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.*
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.max

/**
 *  Basic button GUI. Reacts to left and right clicks.
 *  @param string The text displayed on the button;
 *  @param callback Function which is called when the button is clicked.
 */
class GuiButton(string : String, private val callback : () -> Unit)
    : Gui(), IClickable, IHoverable, IParent, ILayoutPost, IDrawable
{
    private var clickCooldown = 0L

    override val children = listOf(GuiText(string))

    override fun update(deltaTime: Long) {
        clickCooldown = max(0L, clickCooldown - deltaTime)
        super.update(deltaTime)
    }

    override var baseColor = ColorHelper.GuiNeutral

    override fun draw() { Manager.renderer.drawBasicBG(this) }

    override fun applyLayoutPost() {
        LayoutUtils.stretchToFitChildren(this, 10.0)
    }

    override fun onClick(button: Int) {
        if(button == 0 && clickCooldown == 0L) {
            callback()
            clickCooldown = 100L
        }
    }

    override fun onHoverStart() { baseColor = ColorHelper.GuiNeutralLight }

    override fun onHoverStop() { baseColor = ColorHelper.GuiNeutral }
}