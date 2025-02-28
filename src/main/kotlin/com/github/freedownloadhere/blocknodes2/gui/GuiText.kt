package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager

open class GuiText(s : String)
    : Gui(), IDrawable
{
    var str : String = ""
        set(value) {
            field = value
            val fr = Minecraft.getMinecraft().fontRendererObj
            w = fr.getStringWidth(field).toDouble() * Manager.config.textScale
            h = fr.FONT_HEIGHT.toDouble() * Manager.config.textScale
        }

    init {
        str = s
    }

    override var baseColor = ColorHelper.White
    override fun draw() {
        if(str.isEmpty())
            return

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