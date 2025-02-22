package com.github.freedownloadhere.blocknodes2.gui.interfaces

interface ISpecialTranslate {
    /**
     * Will be called at the beginning of `GuiLayout.translate()`.
     *
     * Used to modify data that normally wouldn't be affected
     * during a translation operation.
     *
     * (see ScrollableList for an example).
     */
    fun doSpecialTranslate(dx : Double, dy : Double)
}