package com.github.freedownloadhere.blocknodes2.gui

import java.util.Stack
import kotlin.reflect.KMutableProperty0

object EzGui {
    private var window : GuiWindow? = null
    private val editStack = Stack<GuiList>()

    fun beginWindow(title : String) {
        assert(editStack.empty())
        window = GuiWindow(0, 0, title)
        editStack.push(window!!.contents.contents)
    }

    fun endWindow() {
        assert(window != null)
        assert(editStack.isNotEmpty())
        GuiManager.base = window!!
        val screenW = GuiManager.width.toDouble()
        val screenH = GuiManager.height.toDouble()
        window!!.dynamicResize()
        window!!.translateCenter(screenW / 2.0, screenH / 2.0)
        editStack.pop()
        assert(editStack.empty())
    }

    fun beginList() {
        assert(editStack.isNotEmpty())
        val top = editStack.peek()
        val newTop = top.newScrollableList(200, 400)
        editStack.push(newTop.contents)
    }

    fun endList() {
        assert(editStack.isNotEmpty())
        editStack.pop()
    }

    fun text(s : String) {
        assert(editStack.isNotEmpty())
        val top = editStack.peek()
        top.newText(s)
    }

    fun button(s : String, callback : () -> Unit) {
        assert(editStack.isNotEmpty())
        val top = editStack.peek()
        top.newButton(s, callback)
    }

    fun textBox(contents : KMutableProperty0<String>, placeholder : String) {
        assert(editStack.isNotEmpty())
        val top = editStack.peek()
        top.newTextBox(contents, placeholder)
    }
}