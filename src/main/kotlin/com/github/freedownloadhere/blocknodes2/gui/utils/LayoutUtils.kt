package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ISpecialTranslate
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

        fun scale(scaleMult : Double) : Rectangle {
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
    }

    fun <T> list(gui : T, xSscale : Double, ySscale : Double, startH : Double = gui.y) : Double
    where T : Gui, T : IParent {
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

    fun <T> stretchToFit(gui : T)
    where T : Gui, T : IParent {
        stretchToFitWidth(gui)
        stretchToFitHeight(gui)
    }

    private fun <T> stretchToFitWidth(gui : T)
    where T : Gui, T : IParent {
        var x1 = Double.POSITIVE_INFINITY
        var x2 = Double.NEGATIVE_INFINITY

        for(child in gui.children) {
            x1 = min(x1, child.x)
            x2 = max(x2, child.x + child.w)
        }

        gui.x = x1
        gui.w = (x2 - x1)
    }

    fun <T> stretchToFitHeight(gui : T)
    where T : Gui, T : IParent {
        var y1 = Double.POSITIVE_INFINITY
        var y2 = Double.NEGATIVE_INFINITY

        for(child in gui.children) {
            y1 = min(y1, child.y)
            y2 = max(y2, child.y + child.h)
        }

        gui.y = y1
        gui.h = (y2 - y1)
    }

    private fun scale(gui : Gui, scaleMult : Double) {
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