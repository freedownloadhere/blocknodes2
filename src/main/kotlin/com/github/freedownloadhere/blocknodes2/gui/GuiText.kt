package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.mixin.AccessorFontRenderer
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11

class GuiText(
    x: Double, y: Double, w: Double, h: Double,
    private val str : String
) : Gui(x, y, w, h) {
    override fun draw() {
        val fr = Minecraft.getMinecraft().fontRendererObj
        val fontTex = (fr as AccessorFontRenderer).fontLocation_blocknodes2
        val strWidth = fr.getStringWidth(str).toDouble()
        val strHeight = fr.FONT_HEIGHT

        GlStateManager.enableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.loadIdentity()
        GlStateManager.translate(x, y, 0.0)
        GlStateManager.scale(w / strWidth, h / strHeight, 1.0)

        Minecraft.getMinecraft().textureManager.bindTexture(fontTex)

        fr.drawString(str, 0, 0, ColorHelper.White.toPackedARGB())

        GlStateManager.popMatrix()
    }
}