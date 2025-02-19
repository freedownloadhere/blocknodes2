package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ScissorStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11
import java.time.Instant

object GuiManager : GuiScreen() {
    private lateinit var window : GuiWindow

    private var lastMouseX = -1
    private var lastMouseY = -1
    private var lastTime = 0L
    private var lastDwheel = 0

    var focused : GuiInteractable? = null
        private set
    var hovered : GuiInteractable? = null
        private set

    object DefaultConfig {
        const val BORDER_THICKNESS = 2.0
        const val TEXT_SCALE = 2.0
        const val SCROLL_SENS = 10
    }

    override fun initGui() {
        super.initGui()
        width = Minecraft.getMinecraft().displayWidth
        height = Minecraft.getMinecraft().displayHeight
        lastTime = Instant.now().toEpochMilli()

        window = GuiWindow(300, 200, "Scroll list test")

        val list = window.contents.newScrollableList(400, 300)
        list.contents.newText("This is a scrollable list")
        for(i in 1..5)
            list.contents.newButton("Button $i") { ChatHelper.send("Clicked button $i") }
        list.contents.newText("I am just a text")
        list.contents.newTextBox("Type something here")
        for(i in 5..10)
            list.contents.newButton("Button $i") { ChatHelper.send("Clicked button $i") }

        val list1 = list.contents.newScrollableList(400, 300)
        list1.contents.newText("This is another scrollable list")
        for(i in 1..5)
            list1.contents.newButton("Button $i") { ChatHelper.send("Clicked another button $i") }
        list1.contents.newText("I am just another text")
        list1.contents.newTextBox("Type something else here")
        for(i in 5..10)
            list1.contents.newButton("Button $i") { ChatHelper.send("Clicked another button $i") }
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

        ScissorStack.enable()
        window.update(deltaTime)
        ScissorStack.disable()

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
        lastDwheel = Mouse.getEventDWheel()

        val lastMouseOn = window.getMouseOn(lastMouseX.toDouble(), lastMouseY.toDouble())

        if(Mouse.getEventButtonState())
            handleMouseClick(lastMouseOn, Mouse.getEventButton())
        handleMouseHover(lastMouseOn)

        if(lastDwheel != 0)
            handleMouseScroll()
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        super.keyTyped(typedChar, keyCode)
        focused?.onKeyTyped(typedChar, keyCode)
    }

    private fun handleMouseClick(lastMouseOn : GuiInteractable?, lastMouseButton : Int) {
        focused = lastMouseOn
        focused?.onClick(lastMouseButton)
    }

    private fun handleMouseHover(lastMouseOn : GuiInteractable?) {
        hovered = lastMouseOn
        hovered?.onHover()
    }

    private fun handleMouseScroll() {
        val d = lastDwheel / DefaultConfig.SCROLL_SENS
        if(hovered != null)
            hovered!!.onScroll(d)
        else
            focused?.onScroll(d)
    }

    override fun drawBackground(tint: Int) { }

    override fun doesGuiPauseGame(): Boolean = false
}