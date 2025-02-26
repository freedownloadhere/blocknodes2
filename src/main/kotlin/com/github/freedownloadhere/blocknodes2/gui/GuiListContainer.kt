package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.*
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.gui.utils.TextUtils
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import kotlin.math.max
import kotlin.math.min

class GuiListContainer
    : Gui(), IScrollable, IDrawable, ILayoutPost, IParentVariadic, ISpecialTranslate
{
    private var start = 0.0
    private var listHeight = 0.0

    override var baseColor = ColorHelper.GuiNeutral
    override val children = mutableListOf<Gui>()

    override var applyLayoutPost = {
        val listScale = Manager.config.listSpacingScale
        listHeight = LayoutUtils.list(this, listScale, listScale, start)
    }

    override fun doSpecialTranslate(dx: Double, dy: Double) { start += dy }

    override fun draw() {
        Manager.renderer.scissorStack.push(this)
        Manager.renderer.drawBasicBG(this)
        Manager.renderer.scissorStack.pop()
    }

    override fun onScroll(d: Int) {
        if(listHeight <= h)
            return
        start += d
        start = min(start, y)
        start = max(start, y - listHeight + h)
    }

    override fun update(deltaTime: Long) {
        for(child in children) {
            if(child.y + child.h <= y || child.y >= y + h)
                child.disable()
            else
                child.enable()
        }
        super.update(deltaTime)
    }
}