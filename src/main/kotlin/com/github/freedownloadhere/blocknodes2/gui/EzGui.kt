package com.github.freedownloadhere.blocknodes2.gui

import java.util.*

object EzGui {
    private var window : GuiWindow? = null
    private var editStack = Stack<GuiList>()

    fun beginWindow(title : String = "Window") {
        val guiW = GuiManager.width.toDouble()
        val guiH = GuiManager.height.toDouble()
        window = GuiWindow(0, 0, title)
        window!!.extendToFill(0.0, 0.0, guiW, guiH, 0.8)
        window!!.translateCenter(guiW * 0.5, guiH * 0.5)
        editStack.push(window!!.contents)
    }

    fun endWindow() {
        assert(editStack.isNotEmpty())
        editStack.pop()
        assert(editStack.empty())
        GuiManager.base = window
    }

    fun text(contents : String) {
        assert(editStack.isNotEmpty())
        editStack.peek().newText(contents)
    }

    fun textBox(placeholder : String = "Text Box") {
        assert(editStack.isNotEmpty())
        editStack.peek().newTextBox(placeholder)
    }

    fun button(contents : String, callback : () -> Unit) {
        assert(editStack.isNotEmpty())
        editStack.peek().newButton(contents, callback)
    }

    fun beginList() {
        assert(editStack.isNotEmpty())
        val last = editStack.peek()
        editStack.push(last.newScrollableList(100, 100).contents)
    }

    fun endList() {
        assert(editStack.isNotEmpty())
        editStack.pop()
    }
}