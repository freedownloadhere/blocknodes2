package com.github.freedownloadhere.blocknodes2.gui

import net.minecraft.client.gui.GuiScreen
import org.lwjgl.opengl.GL11

object GuiManager : GuiScreen() {
    private val root = GuiBase(200.0, 200.0, 200.0, 100.0)
    private var focused : GuiBase? = null

    init {
        root.addChild(GuiBase(10.0, 10.0, 50.0, 50.0))
    }

    object DefaultConfig {
        const val HL_THICKNESS = 2.0
        const val BORDER_THICKNESS = 1.0
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glPushMatrix()

        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_LIGHTING)

        root.renderUpdate(focused)

        GL11.glPopMatrix()
        GL11.glPopAttrib()
    }

    override fun initGui() { }

    override fun drawBackground(tint: Int) { }

    override fun doesGuiPauseGame(): Boolean = false

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        focused = root.mouseClickedEvent(mouseX.toDouble(), mouseY.toDouble(), mouseButton)
    }
}