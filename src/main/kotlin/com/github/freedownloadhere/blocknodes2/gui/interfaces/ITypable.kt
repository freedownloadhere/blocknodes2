package com.github.freedownloadhere.blocknodes2.gui.interfaces

interface ITypable : IInteractable {
    fun onKeyTyped(typedChar : Char, keyCode : Int)
}