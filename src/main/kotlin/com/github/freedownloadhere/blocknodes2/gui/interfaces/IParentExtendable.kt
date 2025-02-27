package com.github.freedownloadhere.blocknodes2.gui.interfaces

import com.github.freedownloadhere.blocknodes2.gui.Gui

interface IParentExtendable : IParent {
    override val children: MutableList<Gui>

    fun addChild(child : Gui) {
        children.add(child)
    }
}