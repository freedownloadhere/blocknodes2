package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.GuiWindow
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen

object Manager : GuiScreen() {
    private var base : Gui? = null
    private lateinit var timeUtil : TimeUtil
    private lateinit var inputManager : InputManager
    lateinit var interactionManager : InteractionManager
        private set
    lateinit var renderer : Renderer
        private set

    object DefaultConfig {
        const val BORDER_THICKNESS = 2.0
        const val TEXT_SCALE = 2.0
    }

    override fun initGui() {
        super.initGui()
        width = Minecraft.getMinecraft().displayWidth
        height = Minecraft.getMinecraft().displayHeight
        base = GuiWindow("Window Title")
        timeUtil = TimeUtil()
        inputManager = InputManager()
        interactionManager = InteractionManager(inputManager, base)
        renderer = Renderer()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val deltaTime = timeUtil.newDeltaTime()

        drawDefaultBackground()
        renderer.beginGuiState()
        base?.update(deltaTime)
        renderer.endGuiState()
    }

    override fun handleMouseInput() {
        inputManager.updateMouse()
        interactionManager.handleMouseInput()
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        super.keyTyped(typedChar, keyCode)
        interactionManager.handleKeyTyped(typedChar, keyCode)
    }

    override fun doesGuiPauseGame(): Boolean = false
}