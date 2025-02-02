package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.node.Node
import com.github.freedownloadhere.blocknodes2.node.NodeScene
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.util.BlockPos

class BlockNodesGui(private val nodeScene : NodeScene) : GuiScreen() {
    private lateinit var inputX : GuiTextField
    private lateinit var inputY : GuiTextField
    private lateinit var inputZ : GuiTextField
    private lateinit var addNodeButton : GuiButton

    override fun initGui() {
        super.initGui()
        val fontRenderer = Minecraft.getMinecraft().fontRendererObj
        inputX = GuiTextField(0, fontRenderer, 10, 25, 30, 20)
        inputY = GuiTextField(1, fontRenderer, 45, 25, 30, 20)
        inputZ = GuiTextField(2, fontRenderer, 80, 25, 30, 20)
        addNodeButton = GuiButton(3, 10, 40, "Add Node")
        buttonList.add(addNodeButton)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val fontRenderer = Minecraft.getMinecraft().fontRendererObj
        drawDefaultBackground()
        drawString(fontRenderer, "Add Node", 10, 10, 0xffffffff.toInt())
        inputX.drawTextBox()
        inputY.drawTextBox()
        inputZ.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        inputX.mouseClicked(mouseX, mouseY, mouseButton)
        inputY.mouseClicked(mouseX, mouseY, mouseButton)
        inputZ.mouseClicked(mouseX, mouseY, mouseButton)
        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        inputX.textboxKeyTyped(typedChar, keyCode)
        inputY.textboxKeyTyped(typedChar, keyCode)
        inputZ.textboxKeyTyped(typedChar, keyCode)
        super.keyTyped(typedChar, keyCode)
    }

    override fun actionPerformed(button: GuiButton?) {
        super.actionPerformed(button)
        if(button == null) return
        if(button.id == 3) {
            val pos = BlockPos(
                inputX.text.toInt(),
                inputY.text.toInt(),
                inputZ.text.toInt()
            )
            nodeScene.nodeList.add(Node(pos, listOf()))
        }
    }

    override fun doesGuiPauseGame(): Boolean = false
}