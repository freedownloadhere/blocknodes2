package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ISpecialTranslate
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object LayoutUtils {
    /**
     * Places all the elements in the GUI in a list-like fashion.
     * @param gui The GUI whose children are to be ordered;
     * @param xS Spacing between elements on X axis;
     * @param yS Spacing between elements on Y axis;
     * @return The added height of all the elements (plus spacing).
     */
    fun list(gui : Gui, xS : Double, yS : Double, startH : Double = gui.y) : Double {
        if(gui !is IParent)
            return Double.NaN

        var finalH = startH + yS
        for(child in gui.children) {
            setPosition(child, gui.x + xS, finalH)
            finalH += child.h + yS
        }

        return finalH - startH
    }

    fun scaleInRectangle(gui : Gui, x1 : Double, y1 : Double, x2 : Double, y2 : Double, paddingMult : Double = 1.0) {
        val dx = abs(x2 - x1)
        val dy = abs(y2 - y1)
        val sf = if(gui.h * (dx / gui.w) > dy) dy / gui.h else dx / gui.w
        gui.w *= sf * paddingMult
        gui.h *= sf * paddingMult
    }

    fun centerInRectangle(gui : Gui, x1 : Double, y1 : Double, x2 : Double, y2 : Double) {
        val cX = 0.5 * (x1 + x2)
        val cY = 0.5 * (y1 + y2)
        gui.x = cX - 0.5 * gui.w
        gui.y = cY - 0.5 * gui.h
    }

    /**
     * Scales the first length(scaleMults) children of a GUI.
     *
     * Usage example:
     *
     * ```
     * height = 100.0, scaleMults = { 0.3, 1.2, 0.8 }
     *
     * child #1 : h becomes 100.0 * 0.3 = 30.0;
     * child #2 : h becomes 100.0 * 1.2 = 120.0;
     * child #3 : h becomes 100.0 * 0.8 = 80.0.
     * ```
     */
    fun heightScaling(gui : Gui, height : Double, scaleMults : Array<Double>) {
        if(gui !is IParent)
            return
        var i = 0
        for(child in gui.children) {
            if(i >= scaleMults.size) break
            child.h = height * scaleMults[i]
            i++
        }
    }

    fun makeChildrenSameWidth(gui : Gui, width : Double) {
        if(gui !is IParent)
            return
        for(child in gui.children)
            child.w = width
    }

    fun stretchToFitChildren(gui : Gui, padding : Double) {
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