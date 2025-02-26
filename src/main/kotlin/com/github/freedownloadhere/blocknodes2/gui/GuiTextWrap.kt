package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.interfaces.IDrawable
import com.github.freedownloadhere.blocknodes2.gui.utils.Manager
import com.github.freedownloadhere.blocknodes2.gui.utils.TextUtils
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager

open class GuiTextWrap(
    str : String,
    parent : Gui,
    private val scaleMult : Double = 1.0
)
    : Gui(), IDrawable
{
    private var rows = TextUtils.wordWrap(str, parent, scaleMult)

    override var baseColor = ColorHelper.White
    override fun draw() {
        Manager.renderer.beginTextState()
        val fr = Minecraft.getMinecraft().fontRendererObj
        val fontHeight = fr.FONT_HEIGHT
        var renderY = y
        for(row in rows) {
            GlStateManager.pushMatrix()
            GlStateManager.translate(x, renderY, 0.0)
            GlStateManager.scale(scaleMult, scaleMult, 1.0)
            fr.drawStringWithShadow(row, 0.0f, 0.0f, baseColor.toPackedARGB())
            GlStateManager.popMatrix()
            renderY += fontHeight * scaleMult
        }
        Manager.renderer.endTextState()
    }
}