package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPost
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ITypable
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import org.lwjgl.input.Keyboard
import kotlin.math.max
import kotlin.math.min

class GuiTextBox(private val placeholder : String)
    : Gui(), ITypable, IDrawable, ILayoutPost, IParent
{
    override var baseColor = ColorHelper.GuiNeutralDark
    override val children = listOf(GuiText(placeholder))
    private val textGui : GuiText
        get() = children[0]
    private val builder = StringBuilder()
    private var cursorPos = 0

    init { textGui.baseColor = ColorHelper.GuiNeutralLight }

    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
        try {
            if(specialKeyMap.containsKey(keyCode))
                specialKeyMap[keyCode]!!.invoke(this)
            else if(!typedChar.isISOControl()) {
                builder.insert(cursorPos, typedChar)
                cursorPos++
            }

            textGui.str = if(builder.isEmpty()) {
                textGui.baseColor = ColorHelper.GuiNeutralLight
                placeholder
            } else {
                textGui.baseColor = ColorHelper.White
                builder.toString()
            }
        } catch(e : IndexOutOfBoundsException) {
            println(e.message)
        }
    }

    private companion object {
        val specialKeyMap : Map<Int, (GuiTextBox) -> Unit> = mapOf(
            Keyboard.KEY_BACK to {
                instance : GuiTextBox ->

                if(instance.builder.isNotEmpty() && instance.cursorPos > 0) {
                    instance.builder.deleteAt(instance.cursorPos - 1)
                    instance.cursorPos--
                    instance.cursorPos = max(instance.cursorPos, 0)
                }
            },
            Keyboard.KEY_RIGHT to {
                instance : GuiTextBox ->

                instance.cursorPos++
                instance.cursorPos = min(instance.cursorPos, instance.builder.length)
            },
            Keyboard.KEY_LEFT to {
                instance : GuiTextBox ->

                instance.cursorPos--
                instance.cursorPos = max(instance.cursorPos, 0)
            }
        )
    }

    override fun applyLayoutPost() {
        LayoutUtils.stretchToFitHeight(this)
        LayoutUtils.list(this, 0.0, 0.0)
    }

    override fun draw() {
        Manager.renderer.drawBasicBG(this)
    }

    override fun update(deltaTime: Long) {
        Manager.renderer.scissorStack.push(this)
        super.update(deltaTime)
        Manager.renderer.scissorStack.pop()
    }
}