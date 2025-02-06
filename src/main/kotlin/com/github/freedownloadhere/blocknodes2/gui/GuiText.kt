package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11

class GuiText(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    var text : String
) : GuiBase(x, y, width, height) {
    override fun draw() {
        drawText(text)
    }
}