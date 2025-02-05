package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11

class GuiText(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    private val text : String
) : GuiBase(x, y, width, height) {
    override fun draw() {
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().fontRendererObj.drawString(text, x.toInt(), y.toInt(), ColorHelper.GuiDefaultBorder.toPackedARGB())
        GL11.glPopAttrib()
    }
}