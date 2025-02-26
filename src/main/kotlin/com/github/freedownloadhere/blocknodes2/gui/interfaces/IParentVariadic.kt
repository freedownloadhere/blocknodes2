package com.github.freedownloadhere.blocknodes2.gui.interfaces

import com.github.freedownloadhere.blocknodes2.gui.Gui

interface IParentVariadic : IParent {
    override val children: MutableList<Gui>
    fun addChild(child : Gui) {
        children.add(child)
    }
}