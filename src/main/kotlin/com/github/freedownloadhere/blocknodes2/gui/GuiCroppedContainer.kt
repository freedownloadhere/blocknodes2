package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ScissorStack

open class GuiCroppedContainer(width : Int, height : Int) : GuiList() {
    init {
        w = width.toDouble()
        h = height.toDouble()
        flagList.add(Flags.ListStaticSize)
    }

    override fun update(deltaTime: Long) {
        ScissorStack.push(this)
        ScissorStack.apply()
        super.update(deltaTime)
        ScissorStack.pop()
    }
}