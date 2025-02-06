package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11

class GuiText(var text : String = "Text") : Gui() {
    override fun draw() {
        val fr = Minecraft.getMinecraft().fontRendererObj

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(GuiManager.DefaultConfig.font)

        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPushMatrix()
        GL11.glLoadIdentity()
        GL11.glTranslated(x, y, 0.0)
        val strWidth = fr.getStringWidth(text).toDouble()
        val strHeight = fr.FONT_HEIGHT.toDouble()
        GL11.glScaled(w / strWidth, h / strHeight, 1.0)

        fr.drawString(
            text, 0, 0,
            ColorHelper.GuiDefaultBorder.toPackedARGB()
        )

        GL11.glPopMatrix()
        GL11.glPopAttrib()
    }
}