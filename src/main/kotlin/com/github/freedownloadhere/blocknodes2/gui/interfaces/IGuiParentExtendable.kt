package com.github.freedownloadhere.blocknodes2.gui.interfaces

import com.github.freedownloadhere.blocknodes2.gui.Gui

interface IGuiParentExtendable : IGuiParent {
    override val children: MutableList<Gui>
    fun addChild(child : Gui)
}