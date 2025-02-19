package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.mixin.AccessorFontRenderer
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11
import kotlin.math.min

class GuiText(private var str : String) : Gui() {
    var textCol = ColorHelper.White

    init {
        updateText(str)
    }

    fun updateText(newStr : String) {
        str = newStr
        w = Minecraft.getMinecraft().fontRendererObj.getStringWidth(newStr).toDouble() * GuiManager.DefaultConfig.TEXT_SCALE
        h = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT.toDouble() * GuiManager.DefaultConfig.TEXT_SCALE
    }

    override fun draw() {
        val fr = Minecraft.getMinecraft().fontRendererObj
        val fontTex = (fr as AccessorFontRenderer).fontLocation_blocknodes2
        val strW = fr.getStringWidth(str).toDouble()
        val strH = fr.FONT_HEIGHT

        GlStateManager.enableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.loadIdentity()
        GlStateManager.translate(x, y, 0.0)
        GlStateManager.scale(w / strW, h / strH, 1.0)

        Minecraft.getMinecraft().textureManager.bindTexture(fontTex)

        fr.drawStringWithShadow(str, 0.0f, 0.0f, textCol.toPackedARGB())

        GlStateManager.disableBlend()
        GlStateManager.disableTexture2D()
        GlStateManager.popMatrix()
    }

    override fun scale(wMult: Double, hMult: Double) {
        val min = min(wMult, hMult)
        super.scale(min, min)
    }
}