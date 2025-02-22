package com.github.freedownloadhere.blocknodes2.gui.interfaces

interface IGuiTypable : IGuiInteractable {
    fun onKeyTyped(typedChar : Char, keyCode : Int)
}