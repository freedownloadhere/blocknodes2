package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.action.NodeAction
import com.github.freedownloadhere.blocknodes2.action.NodeActionHoldKey
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

        root = GuiWindow(300, 200, "Add Node")

        with(root.contents) {
            newText("Coordinates:")
            val xText = newTextBox("X Position")
            val yText = newTextBox("Y Position")
            val zText = newTextBox("Z Position")
            newText("Actions:")

            val list = mutableListOf<NodeAction>()
            val actionListGui = newScrollableList(100, 200)

            newButton("Add Walk Forward") {
                list.add(NodeActionHoldKey(Minecraft.getMinecraft().gameSettings.keyBindForward))
                actionListGui.contents.addChild(GuiText("Walk Forward"))
            }
            newButton("Add Walk Backward") {
                list.add(NodeActionHoldKey(Minecraft.getMinecraft().gameSettings.keyBindBack))
                actionListGui.contents.addChild(GuiText("Walk Backward"))
            }
            newButton("Add Walk Left") {
                list.add(NodeActionHoldKey(Minecraft.getMinecraft().gameSettings.keyBindLeft))
                actionListGui.contents.addChild(GuiText("Walk Left"))
            }

            newButton("Add") {
                val pos = BlockPos(
                    xText.contents.toInt(),
                    yText.contents.toInt(),
                    zText.contents.toInt()
                )
                val node = Node(pos, list)
                ModState.loadedNodeScene.nodeList.add(node)
                ChatHelper.send("Added node")
            }
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
        lastDwheel = Mouse.getEventDWheel()

        val lastMouseOn = root.getMouseOn(lastMouseX.toDouble(), lastMouseY.toDouble())

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