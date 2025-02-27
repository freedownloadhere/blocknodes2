package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IListLayout
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ISpecialTranslate
import net.minecraft.client.Minecraft
import kotlin.math.max
import kotlin.math.min

/**
 * All `LayoutUtils` function calls begin with a `gui` parameter.
 *
 * This is the GUI that will be affected (or whose children will be affected) in the operation.
 */
object LayoutUtils {
    class Rectangle(
        private var x1 : Double,
        private var y1 : Double,
        private var x2 : Double,
        private var y2 : Double
    ) {
        constructor(gui : Gui) : this(gui.x, gui.y, gui.x + gui.w, gui.y + gui.h)

        val w : Double
            get() = x2 - x1
        val h : Double
            get() = y2 - y1
        val centerX : Double
            get() = 0.5 * (x1 + x2)
        val centerY : Double
            get() = 0.5 * (y1 + y2)

        companion object {
            val wholeScreen : Rectangle
                get() = Rectangle(0.0, 0.0, Manager.width.toDouble(), Manager.height.toDouble())
        }

        fun scaleCentered(scaleMult : Double) : Rectangle {
            val cx = centerX
            val cy = centerY
            val dx = 0.5 * (x2 - x1)
            val dy = 0.5 * (y2 - y1)
            val sm = 1.0 - 2.0 * scaleMult
            x1 = cx - dx * sm
            x2 = cx + dx * sm
            y1 = cy - dy * sm
            y2 = cy + dy * sm
            return this
        }

        fun shrink(eachSideBy : Double) : Rectangle {
            x1 += eachSideBy
            y1 += eachSideBy
            x2 -= eachSideBy
            y2 -= eachSideBy
            return this
        }
    }

    /**
     * Places all the elements in the GUI in a list-like fashion.
     * @param gui The GUI whose children are to be ordered;
     * @param xSscale Spacing between elements on X axis when multiplied by `gui.w`;
     * @param ySscale Spacing between elements on Y axis when multiplied by `gui.h`;
     * @return The added height of all the elements (plus spacing).
     */
    fun list(gui : Gui, xSscale : Double, ySscale : Double, startH : Double = gui.y) : Double {
        if(gui !is IParent)
            return Double.NaN

        val xS = xSscale * gui.w
        val yS = ySscale * gui.h

        var finalH = startH + yS
        for(child in gui.children) {
            setPosition(child, gui.x + xS, finalH)
            finalH += child.h + yS
        }

        return finalH - startH
    }

    fun setAspectRatio(gui : Gui, aspectRatio : Double) {
        gui.w = aspectRatio
        gui.h = 1.0
    }

    fun scaleIn(gui : Gui, rect : Rectangle, paddingMult : Double = 1.0) {
        val sf = if(gui.h * (rect.w / gui.w) > rect.h) rect.h / gui.h else rect.w / gui.w
        scale(gui, sf * paddingMult)
    }

    fun scaleHeightTo(gui : Gui, newH : Double) {
        scale(gui, newH / gui.h)
    }

    fun scaleWidthTo(gui : Gui, newW : Double) {
        scale(gui, newW / gui.w)
    }

    fun centerIn(gui : Gui, rect : Rectangle) {
        setPosition(gui, rect.centerX - 0.5 * gui.w, rect.centerY - 0.5 * gui.h)
    }

    fun stretchToFit(gui : Gui, padding : Double) {
        if(gui !is IParent)
            return

        var x1 = Double.MAX_VALUE
        var y1 = Double.MAX_VALUE
        var x2 = Double.MIN_VALUE
        var y2 = Double.MIN_VALUE

        for(child in gui.children) {
            x1 = min(x1, child.x)
            y1 = min(y1, child.y)
            x2 = max(x2, child.x + child.w)
            y2 = max(y2, child.y + child.h)
        }

        gui.x = x1 - padding
        gui.y = y1 - padding
        gui.w = (x2 - x1) + padding
        gui.h = (y2 - y1) + padding
    }

    fun stretchToFit(gui : Gui, rows : List<String>, scaleMult : Double = 1.0) {
        val fr = Minecraft.getMinecraft().fontRendererObj
        gui.w = 0.0
        gui.h = 0.0
        for(row in rows) {
            gui.w = max(gui.w, fr.getStringWidth(row).toDouble() * scaleMult)
            gui.h += fr.FONT_HEIGHT * scaleMult
        }
    }

    fun scale(gui : Gui, scaleMult : Double) {
        gui.w *= scaleMult
        gui.h *= scaleMult
    }

    private fun setPosition(gui : Gui, newX : Double, newY : Double) {
        translate(gui, newX - gui.x, newY - gui.y)
    }

    private fun translate(gui : Gui, dx : Double, dy : Double) {
        if(gui is ISpecialTranslate)
            gui.doSpecialTranslate(dx, dy)
        gui.x += dx
        gui.y += dy
        if(gui is IParent)
            for(child in gui.children)
                translate(child, dx, dy)
    }
}