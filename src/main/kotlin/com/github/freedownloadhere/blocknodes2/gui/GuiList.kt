package com.github.freedownloadhere.blocknodes2.gui

class GuiList : GuiInteractable() {
    var spacing = 0.0

    override fun draw() { }

    override fun addChild(child: Gui) {
        super.addChild(child)
        child.translateSetXY(x, y + h)
        h += child.h + spacing
    }
}