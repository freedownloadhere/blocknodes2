package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.ILayoutPre
import com.github.freedownloadhere.blocknodes2.gui.utils.LayoutUtils
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager

class GuiParagraph(str : String, private val parent : Gui)
    : GuiText(str), ILayoutPre
{
    override fun applyLayoutPre() {
        updateText(str)
        LayoutUtils.scale(this, Manager.config.textScale)
    }

    override fun draw() {
        Manager.renderer.beginTextState()
        // inefficient lols
        val fr = Minecraft.getMinecraft().fontRendererObj
        val scaleMultX = w / fr.getStringWidth(str).toDouble()
        val scaleMultY = h / fr.FONT_HEIGHT
        val rows = LayoutUtils.wordWrap(this, LayoutUtils.Rectangle(parent).shrink(0.025 * parent.w))
        var renderY = y
        for(row in rows) {
            GlStateManager.pushMatrix()
            GlStateManager.translate(x, renderY, 0.0)
            GlStateManager.scale(scaleMultX, scaleMultY, 1.0)
            fr.drawStringWithShadow(row, 0.0f, 0.0f, ColorHelper.White.toPackedARGB())
            GlStateManager.popMatrix()
            renderY += h
        }
        Manager.renderer.endTextState()
    }
}