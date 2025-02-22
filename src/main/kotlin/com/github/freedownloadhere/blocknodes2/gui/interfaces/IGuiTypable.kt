package com.github.freedownloadhere.blocknodes2.gui.interfaces

interface IGuiTypable : IInteractable {
    fun onKeyTyped(typedChar : Char, keyCode : Int)
}