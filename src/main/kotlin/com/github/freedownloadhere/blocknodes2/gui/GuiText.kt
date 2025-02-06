package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.mixin.AccessorFontRenderer
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11

class GuiText(
    x: Double,
    y: Double,
    w: Double,
    h: Double,
    private var str : String
) : Gui(x, y, w, h) {
    fun changeText(s : String) : GuiText {
        str = s
        return this
    }

    override fun draw() {
        val fr = Minecraft.getMinecraft().fontRendererObj
        val font = (fr as AccessorFontRenderer).fontLocation_blocknodes2
        val strWidth = fr.getStringWidth(str).toDouble()

        drawBorder()
        drawBG()

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glEnable(GL11.GL_TEXTURE_2D)

        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPushMatrix()
        GL11.glLoadIdentity()
        GL11.glTranslated(x, y, 0.0)
        GL11.glScaled(w / strWidth, w / strWidth, 1.0)

        GL11.glBindTexture(
            GL11.GL_TEXTURE_2D,
            Minecraft.getMinecraft().textureManager.getTexture(font).glTextureId
        )

        fr.drawString(
            str, 0, 0,
            ColorHelper.White.toPackedARGB()
        )

        GL11.glPopMatrix()
        GL11.glPopAttrib()
    }
}