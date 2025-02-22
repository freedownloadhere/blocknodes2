package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiOrdered
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IGuiParent

abstract class Gui {
    internal var x = 0.0
    internal var y = 0.0
    internal var w = 1.0
    internal var h = 1.0

    var toggled = true
        private set

    open fun update(deltaTime : Long) {
        if(!toggled) return
        if(this is IGuiOrdered)
            applyOrdering()
        if(this is IGuiDrawable)
            draw()
        if(this is IGuiParent)
            for(child in children)
                child.update(deltaTime)
    }

    open fun toggle() {
        toggled = !toggled
        if(this !is IGuiParent)
            return

        if(toggled)
            for(child in children)
                child.enable()
        else
            for(child in children)
                child.disable()
    }

    open fun enable() {
        toggled = true
        if(this is IGuiParent)
            for(child in children)
                child.enable()
    }

    open fun disable() {
        toggled = false
        if(this is IGuiParent)
            for(child in children)
                child.disable()
    }
}