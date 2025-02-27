package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.*
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParentExtendable
import java.util.*

class WindowBuilder(title : String) {
    private val window = GuiWindow(title)
    private val editStack = Stack<GuiListContainer>()

    init {
        editStack.push(window.contents)
    }

    fun beginList() : WindowBuilder {
        val top = editStack.peek()
        val gui = GuiListContainer()
        top.addChild(gui)
        editStack.push(gui)
        return this
    }

    fun endList() : WindowBuilder {
        val top = editStack.pop()
        top.applyLayoutPost()
        return this
    }

    fun newHeader(str : String) : WindowBuilder {
        val top = editStack.peek()
        val gui = GuiHeader(str, top)
        top.addChild(gui)
        return this
    }

    fun newParagraph(str : String) : WindowBuilder {
        val top = editStack.peek()
        val gui = GuiParagraph(str, top)
        top.addChild(gui)
        return this
    }

    fun finish() : GuiWindow {
        assert(editStack.size == 1)
        editStack.pop()
        assert(editStack.empty())
        window.applyLayout()
        return window
    }
}