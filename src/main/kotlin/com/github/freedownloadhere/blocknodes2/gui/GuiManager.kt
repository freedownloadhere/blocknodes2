package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11
import java.time.Instant

object GuiManager : GuiScreen() {
    private lateinit var root : GuiDropDown

    private var lastMouseX = -1
    private var lastMouseY = -1
    private var lastTime = 0L

    var focused : GuiInteractable? = null
        private set
    var hovered : GuiInteractable? = null
        private set

    object DefaultConfig {
        const val BORDER_THICKNESS = 2.0
    }

    override fun initGui() {
        super.initGui()
        width = Minecraft.getMinecraft().displayWidth
        height = Minecraft.getMinecraft().displayHeight
        lastTime = Instant.now().toEpochMilli()

        root = GuiDropDown("Drop down example")
        root.scaleSetWH(500.0, 30.0)
        root.translateSetXY(500.0, 100.0)
        root.postInit()

        val list = GuiList()
        list.translateSnapTo(root.contents, Gui.SnapDir.Left, 10.0)
        list.translateSnapTo(root.contents, Gui.SnapDir.Top, 10.0)
        root.contents.addChild(list)

        val button1 = GuiButton("Option one") { ChatHelper.send("Picked option one") }
        button1.scaleSetWH(50.0, 30.0)
        list.addChild(button1)

        val button2 = GuiButton("Option two") { ChatHelper.send("Picked option two") }
        button2.scaleSetWH(50.0, 30.0)
        list.addChild(button2)

        list.addChild(GuiText("text three"))
        list.addChild(GuiText("text four"))
        list.addChild(GuiText("text five"))
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val currTime = Instant.now().toEpochMilli()
        val deltaTime = currTime - lastTime
        lastTime = currTime

        drawDefaultBackground()

        GlStateManager.matrixMode(GL11.GL_PROJECTION)
        GlStateManager.pushMatrix()
        GlStateManager.loadIdentity()
        GlStateManager.ortho(0.0, width.toDouble(), height.toDouble(), 0.0, -1.0, 1.0)

        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.loadIdentity()

        GlStateManager.disableTexture2D()
        GlStateManager.disableLighting()

        root.update(deltaTime)

        GlStateManager.enableLighting()
        GlStateManager.enableTexture2D()

        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.popMatrix()
        GlStateManager.matrixMode(GL11.GL_PROJECTION)
        GlStateManager.popMatrix()
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