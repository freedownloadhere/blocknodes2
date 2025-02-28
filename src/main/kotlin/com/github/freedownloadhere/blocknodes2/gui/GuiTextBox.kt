package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPre
import com.github.freedownloadhere.blocknodes2.gui.interfaces.IParent
import com.github.freedownloadhere.blocknodes2.gui.interfaces.ITypable
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import org.lwjgl.input.Keyboard

class GuiTextBox(private val placeholder : String)
    : Gui(), ITypable, IDrawable, ILayoutPre, IParent
{
    private val textGui : GuiText
        get() = children[0]
    private val builder = StringBuilder()

    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
        if(specialKeyMap.containsKey(keyCode))
            specialKeyMap[keyCode]!!.invoke(this)
        else if(!typedChar.isISOControl())
            builder.append(typedChar)

        textGui.str = if(builder.isEmpty()) {
            textGui.baseColor = ColorHelper.GuiNeutralLight
            placeholder
        } else {
            textGui.baseColor = ColorHelper.White
            builder.toString()
        }
    }

    private companion object {
        val specialKeyMap : Map<Int, (GuiTextBox) -> Unit> = mapOf(
            Keyboard.KEY_BACK to {
                instance : GuiTextBox ->
                if(instance.builder.isNotEmpty())
                    instance.builder.deleteAt(instance.builder.length - 1)
            }
        )
    }

    override var baseColor = ColorHelper.GuiNeutralDark
    override val children = listOf(GuiText(placeholder))

    init {
        textGui.baseColor = ColorHelper.GuiNeutralLight
    }

    override fun applyLayoutPre() {
        LayoutUtils.stretchToFitHeight(this, 1.0)
        LayoutUtils.centerIn(textGui, LayoutUtils.Rectangle(this))
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