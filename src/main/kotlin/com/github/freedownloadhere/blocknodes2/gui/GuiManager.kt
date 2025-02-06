package com.github.freedownloadhere.blocknodes2.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11

object GuiManager : GuiScreen() {
    private var root = GuiWindow()
    private var mouseCoords = GuiText()
    private var coolButton = GuiButton()
    private var dropDown = GuiDropDown()

    private var lastMouseX = -1;
    private var lastMouseY = -1;

    var focused : GuiInteractable? = null
        private set
    var hovered : GuiInteractable? = null
        private set

    object DefaultConfig {
        const val HL_THICKNESS = 4.0
        const val BORDER_THICKNESS = 2.0
    }

    override fun initGui() {
        super.initGui()
        width = Minecraft.getMinecraft().displayWidth
        height = Minecraft.getMinecraft().displayHeight

        root = GuiWindow()
            .wh(1000.0, 800.0)
            .center(width / 2.0, height / 2.0)
            .finish() as GuiWindow

        mouseCoords = GuiText()
            .text("Mouse Coords : $lastMouseX $lastMouseY")
            .wh(400.0, 30.0)
            .xy(10.0, 10.0)
            .finish() as GuiText

        coolButton = GuiButton()
            .text("Cool Button")
            .wh(100.0, 50.0)
            .xy(100.0, 100.0)
            .finish() as GuiButton

        dropDown = GuiDropDown()
            .wh(800.0, 30.0)
            .xy(0.0, 0.0)
            .finish() as GuiDropDown

        //root.addChild(mouseCoords)
        root.addChild(coolButton)
        root.addChild(dropDown)
        root.addChild(
            GuiText()
                .text("string 1")
                .wh(100.0, 50.0)
                .xy(10.0, 100.0)
                .finish()
        )
        root.addChild(
            GuiText()
                .text("string 1")
                .wh(100.0, 50.0)
                .xy(10.0, 200.0)
                .finish()
        )
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

        mouseCoords.str = "Mouse coords: $lastMouseX $lastMouseY"
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

    private fun handleMouseClick(lastMouseOn : GuiInteractable?, lastMouseButton : Int) {
        focused = lastMouseOn
        focused?.onClick(lastMouseButton)
    }

    private fun handleMouseHover(lastMouseOn : GuiInteractable?) {
        hovered = lastMouseOn
        hovered?.onHover()
    }

    override fun drawBackground(tint: Int) { }

    override fun doesGuiPauseGame(): Boolean = false
}