package com.github.freedownloadhere.blocknodes2.gui

class GuiList : GuiInteractable() {
    override fun draw() { }

    override fun addChild(child: Gui) {
        super.addChild(child)
        child.translateSetXY(x, y + h)
        h += child.h
    }
}