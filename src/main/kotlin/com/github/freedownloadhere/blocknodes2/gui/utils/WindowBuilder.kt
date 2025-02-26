package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.*
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParentVariadic
import java.util.*

class WindowBuilder(title : String) {
    private val window = GuiWindow(title)
    private val editStack = Stack<Gui>()

    init {
        editStack.push(window.contents)
        window.applyLayoutPre()
    }

    fun beginList() : WindowBuilder {
        val top = editStack.peek()
        val gui = GuiListContainer()
        editStack.push(gui)
        return this
    }

    fun endList() : WindowBuilder {
        val top = editStack.pop()
        assert(top is GuiListContainer)
        (top as GuiListContainer).applyLayoutPost()
        return this
    }

    fun newHeader(str : String) : WindowBuilder {
        val top = editStack.peek()
        assert(top is GuiListContainer)
        (top as GuiListContainer).addHeader(str)
        return this
    }

    fun newParagraph(str : String) : WindowBuilder {
        val top = editStack.peek()
        assert(top is GuiListContainer)
        (top as GuiListContainer).addParagraph(str)
        return this
    }

    fun finish() : GuiWindow {
        assert(editStack.size == 1)
        editStack.pop()
        assert(editStack.empty())
        return window
    }
}