package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.*
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParentVariadic
import java.util.*

class WindowBuilder(title : String) {
    private val window = GuiWindow(title)
    private val editStack = Stack<Gui>()

    init { editStack.push(window.contents) }

    fun beginList() : WindowBuilder {
        val gui = GuiListContainer()
        editStack.push(gui)
        return this
    }

    fun endList() : WindowBuilder {
        val top = editStack.peek()
        assert(top is GuiListContainer)
        editStack.pop()
        return this
    }

    fun newHeader(str : String) : WindowBuilder {
        val top = editStack.peek()
        assert(top is IParentVariadic)
        val gui = GuiHeader(str, top)
        (top as IParentVariadic).addChild(gui)
        return this
    }

    fun newParagraph(str : String) : WindowBuilder {
        val top = editStack.peek()
        assert(top is IParentVariadic)
        val gui = GuiParagraph(str, top)
        (top as IParentVariadic).addChild(gui)
        return this
    }

    fun finish() : GuiWindow {
        assert(editStack.size == 1)
        editStack.pop()
        assert(editStack.empty())
        return window
    }
}