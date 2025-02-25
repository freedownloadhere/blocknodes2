package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.*

abstract class Gui {
    internal var x = 0.0
    internal var y = 0.0
    internal var w = 1.0
    internal var h = 1.0

    var toggled = true
        private set

    open fun update(deltaTime : Long) {
        if(!toggled) return
        // maybe dont every frame
        if(this is IDrawable)
            draw()
        if(this is IParent)
            for(child in children)
                child.update(deltaTime)
    }

    open fun toggle() {
        toggled = !toggled
        if(this !is IParent)
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
        if(this is IParent)
            for(child in children)
                child.enable()
    }

    open fun disable() {
        toggled = false
        if(this is IParent)
            for(child in children)
                child.disable()
    }
}