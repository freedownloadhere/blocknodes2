package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.node.Node
import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ModState
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.BlockPos
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11
import java.time.Instant

object GuiManager : GuiScreen() {
    private lateinit var root : GuiWindow

    private var lastMouseX = -1
    private var lastMouseY = -1
    private var lastTime = 0L

    var focused : GuiInteractable? = null
        private set
    var hovered : GuiInteractable? = null
        private set

    object DefaultConfig {
        const val BORDER_THICKNESS = 2.0
        const val TEXT_SCALE = 2.0
    }

    override fun initGui() {
        super.initGui()
        width = Minecraft.getMinecraft().displayWidth
        height = Minecraft.getMinecraft().displayHeight
        lastTime = Instant.now().toEpochMilli()

        root = GuiWindow("Add Node")
        root.setPosition(300.0, 200.0)
        with(root.contents) {
            this.addChild(GuiText("Input X, Y, Z below:"))
            val list = this.addChild(GuiList()) as GuiList
            val x = list.addChild(GuiTextBox("X Position")) as GuiTextBox
            val y = list.addChild(GuiTextBox("Y Position")) as GuiTextBox
            val z = list.addChild(GuiTextBox("Z Position")) as GuiTextBox
            this.addChild(GuiButton("Add") {
                val pos = BlockPos(x.contents.toInt(), y.contents.toInt(), z.contents.toInt())
                val node = Node(pos, listOf())
                ModState.loadedNodeScene.nodeList.add(node)
                ChatHelper.send("Added new node at $pos")
            })
        }
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

    override fun drawBackground(tint: Int) { }

    override fun doesGuiPauseGame(): Boolean = false
}