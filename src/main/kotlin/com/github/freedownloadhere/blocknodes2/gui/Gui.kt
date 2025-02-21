package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import com.github.freedownloadhere.blocknodes2.util.GuiFlagList

open class Gui {
    internal var x = 0.0
    internal var y = 0.0
    internal var w = 0.0
    internal var h = 0.0
    internal val children = mutableListOf<Gui>()
    var toggled = true
        private set
    var bgColor = ColorHelper.GuiNeutral
    val flagList = GuiFlagList()

    enum class Flags(val v : Int) {
        TransparentBG(1 shl 0),
        ListAllEqualWidths(1 shl 1),
        ListStaticWidth(1 shl 2),
        ListStaticHeight(1 shl 3),
        ListStaticSize(ListStaticWidth.v or ListStaticHeight.v),

        // specifically the parent. doesnt affect children
        ShouldNotInteract(1 shl 4)
    }

    open fun toggle() {
        toggled = !toggled
        if(toggled)
            for(child in children)
                child.enable()
        else
            for(child in children)
                child.disable()
    }

    open fun addChild(child : Gui) : Gui {
        children.add(child)
        return child
    }

    open fun enable() {
        toggled = true
        for(child in children)
            child.enable()
    }

    open fun disable() {
        toggled = false
        for(child in children)
            child.disable()
    }

    open fun setSize(newW : Double, newH : Double) {
        w = newW
        h = newH
    }

    open fun translate(dx : Double, dy : Double) {
        x += dx
        y += dy
        for(child in children)
            child.translate(dx, dy)
    }

    open fun setPosition(newX : Double, newY : Double) {
        translate(newX - x, newY - y)
    }

    open fun translatePlaceRelativeTo(parent : Gui) {
        translate(parent.x, parent.y)
    }

    open fun translateCenter(xCenter : Double, yCenter : Double) {
        val dw = w / 2.0
        val dh = h / 2.0
        setPosition(xCenter - dw, yCenter - dh)
    }

    open fun translateCenterIn(parent : Gui) {
        val xCenter = parent.x + parent.w / 2.0
        val yCenter = parent.y + parent.h / 2.0
        translateCenter(xCenter, yCenter)
    }

    enum class SnapDir { Left, Right, Top, Bottom }
    open fun translateSnapTo(parent : Gui, type : SnapDir, padding : Double = 0.0) {
        when(type) {
            SnapDir.Left -> setPosition(parent.x + padding, y)
            SnapDir.Right -> setPosition(parent.x + parent.w - w - padding, y)
            SnapDir.Top -> setPosition(x, parent.y + padding)
            SnapDir.Bottom -> setPosition(x, parent.y + parent.h - h - padding)
        }
    }

    open fun scale(wMult : Double, hMult : Double) {
        w *= wMult
        h *= hMult

        for(child in children)
            child.scale(wMult, hMult)
    }

    open fun scale(mult : Double) {
        scale(mult, mult)
    }

    open fun update(deltaTime : Long) {
        if(!toggled) return
        if(this is IGuiDrawable)
            draw()
        for(child in children)
            child.update(deltaTime)
    }
}