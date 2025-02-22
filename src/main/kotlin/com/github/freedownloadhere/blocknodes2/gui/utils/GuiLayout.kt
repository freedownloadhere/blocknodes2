package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParent
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object GuiLayout {
    fun list(gui : Gui, xS : Double, yS : Double) {
        if(gui !is IGuiParent)
            return

        var placementH = gui.y + yS
        for(child in gui.children) {
            GuiTranslation.setPosition(child, gui.x + xS, placementH)
            placementH += child.h + yS
        }
    }

    fun scaleInRectangle(gui : Gui, x1 : Double, y1 : Double, x2 : Double, y2 : Double, paddingMult : Double = 1.0) {
        val dx = abs(x2 - x1)
        val dy = abs(y2 - y1)
        val sf = if(gui.h * (dx / gui.w) > dy) dy / gui.h else dx / gui.w
        gui.w *= sf * paddingMult
        gui.h *= sf * paddingMult
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
        if(gui !is IGuiParent)
            return
        var i = 0
        for(child in gui.children) {
            if(i >= scaleMults.size) break
            child.h = height * scaleMults[i]
            i++
        }
    }

    fun makeChildrenSameWidth(gui : Gui, width : Double) {
        if(gui !is IGuiParent)
            return
        for(child in gui.children)
            child.w = width
    }

    fun stretchToFitChildren(gui : Gui, padding : Double) {
        if(gui !is IGuiParent)
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
}