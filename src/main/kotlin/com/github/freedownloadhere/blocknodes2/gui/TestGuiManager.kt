package com.github.freedownloadhere.blocknodes2.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.opengl.GL11

object TestGuiManager : GuiScreen() {
    private val root = GuiBase(0.0, 200.0, 200.0, 100.0)

    init {
        root.children.add(GuiBase(10.0, 10.0, 50.0, 50.0))
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()

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

    override fun initGui() { }

    override fun drawBackground(tint: Int) { }

    override fun doesGuiPauseGame(): Boolean = false

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        root.mouseClickedEvent(mouseX.toDouble(), mouseY.toDouble(), mouseButton)
    }
}