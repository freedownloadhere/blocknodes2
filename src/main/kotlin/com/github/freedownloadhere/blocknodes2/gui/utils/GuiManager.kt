package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.GuiWindow
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen

object GuiManager : GuiScreen() {
    private var base : Gui? = null
    private lateinit var timeManager : GuiTimeManager
    private lateinit var inputManager : GuiInputManager
    lateinit var interactionManager : GuiInteractionManager
        private set
    lateinit var renderer : GuiRenderer
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
        timeManager = GuiTimeManager()
        inputManager = GuiInputManager()
        interactionManager = GuiInteractionManager(inputManager, base)
        renderer = GuiRenderer()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val deltaTime = timeManager.newDeltaTime()

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