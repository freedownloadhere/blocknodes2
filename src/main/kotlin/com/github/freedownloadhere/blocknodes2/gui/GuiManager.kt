package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11

object GuiManager {
    private val root = GuiBase(0.0, 200.0, 200.0, 100.0)

    init {
        root.children.add(GuiBase(10.0, 10.0, 50.0, 50.0))
    }

    object DefaultConfig {
        val hlThickness = 1.0
        val borderThickness = 1.0
    }

    fun draw() {
        val mc = Minecraft.getMinecraft()
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glPushMatrix()

        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_LIGHTING)

        //GL11.glMatrixMode(GL11.GL_PROJECTION)
        //GL11.glLoadIdentity()
        //GL11.glOrtho(0.0, mc.displayWidth.toDouble(), mc.displayHeight.toDouble(), 0.0, -1.0, 1.0)
        GL11.glMatrixMode(GL11.GL_MODELVIEW)

        root.renderUpdate()

        GL11.glPopMatrix()
        GL11.glPopAttrib()
    }

    fun mouseClickedEvent(mouseX : Int, mouseY : Int, button : Int) {
        root.mouseClickedEvent(mouseX.toDouble(), mouseY.toDouble(), button)
    }
}