package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager

open class GuiText(str : String)
    : Gui(), IDrawable
{
    var str : String
        private set

    init {
        this.str = str
        updateText(str)
    }

    protected fun updateText(newStr : String) {
        str = newStr
        val fr = Minecraft.getMinecraft().fontRendererObj
        w = fr.getStringWidth(str).toDouble()
        h = fr.FONT_HEIGHT.toDouble()
    }

    override var baseColor = ColorHelper.White
    override fun draw() {
        Manager.renderer.beginTextState()

        val fr = Minecraft.getMinecraft().fontRendererObj
        val scaleMultX = w / fr.getStringWidth(str).toDouble()
        val scaleMultY = h / fr.FONT_HEIGHT
        GlStateManager.translate(x, y, 0.0)
        GlStateManager.scale(scaleMultX, scaleMultY, 1.0)
        fr.drawStringWithShadow(str, 0.0f, 0.0f, baseColor.toPackedARGB())

        Manager.renderer.endTextState()
    }
}