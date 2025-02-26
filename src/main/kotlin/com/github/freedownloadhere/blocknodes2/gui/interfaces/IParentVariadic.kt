package com.github.freedownloadhere.blocknodes2.gui.interfaces

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.utils.ParentChildLayout

interface IParentVariadic : IParent {
    override val children: MutableList<Gui>
    fun addChild(child : Gui) {
        children.add(child)
        ParentChildLayout.apply(this as Gui, child)
    }
}