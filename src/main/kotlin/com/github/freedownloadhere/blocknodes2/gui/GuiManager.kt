package com.github.freedownloadhere.blocknodes2.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11

object GuiManager : GuiScreen() {
    private val root = GuiBase(200.0, 200.0, 200.0, 100.0)
    var focused : GuiBase? = null
        private set
    var hovered : GuiBase? = null
        private set
    private val mouseCoords = GuiText(10.0, 10.0, 100.0, 20.0, "Mouse coords: ")

    private var lastMouseX = -1;
    private var lastMouseY = -1;

    init {
        root.addChild(mouseCoords)
        root.addChild(GuiButton(10.0, 30.0, 60.0, 20.0, "New button"))
    }

    object DefaultConfig {
        const val HL_THICKNESS = 1.0
        const val BORDER_THICKNESS = 1.0
    }

    override fun initGui() {
        super.initGui()
        width = Minecraft.getMinecraft().displayWidth
        height = Minecraft.getMinecraft().displayHeight
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)

        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glPushMatrix()
        GL11.glLoadIdentity()
        GL11.glOrtho(0.0, width.toDouble(), height.toDouble(), 0.0, -1.0, 1.0)

        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPushMatrix()
        GL11.glLoadIdentity()

        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_LIGHTING)

        mouseCoords.text = "Mouse coords: $lastMouseX $lastMouseY"
        root.update()

        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glPopMatrix()
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPopMatrix()
        GL11.glPopAttrib()
    }

    override fun handleMouseInput() {
        lastMouseX = Mouse.getEventX()
        lastMouseY = height - Mouse.getEventY() - 1

        val lastMouseOn = root.getMouseOn(lastMouseX.toDouble(), lastMouseY.toDouble())

        if(Mouse.getEventButtonState())
            handleMouseClick(lastMouseOn, Mouse.getEventButton())
        handleMouseHover(lastMouseOn)
    }

    private fun handleMouseClick(lastMouseOn : GuiBase?, lastMouseButton : Int) {
        focused = lastMouseOn
        focused?.onClick(lastMouseButton)
    }

    private fun handleMouseHover(lastMouseOn : GuiBase?) {
        hovered = lastMouseOn
        hovered?.onHover()
    }

    override fun drawBackground(tint: Int) { }

    override fun doesGuiPauseGame(): Boolean = false
}