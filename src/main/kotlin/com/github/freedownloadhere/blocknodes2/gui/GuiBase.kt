package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

open class GuiBase(
    protected var x : Double,
    protected var y : Double,
    private var width : Double,
    private var height : Double
) {
    private val children = mutableListOf<GuiBase>()

    fun addChild(gui : GuiBase) {
        gui.x += x
        gui.y += y
        children.add(gui)
    }

    fun renderUpdate() {
        draw()
        for(child in children)
            child.renderUpdate()
    }

    protected open fun draw() {
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()

        drawBG()
    }

    private fun drawBorder() {
        val t = GuiManager.DefaultConfig.BORDER_THICKNESS
        GL11.glPushMatrix()
        GL11.glTranslated(x - t, y - t, 0.0)
        GL11.glScaled(width + 2 * t, height + 2 * t, 1.0)
        drawRect(ColorHelper.GuiDefaultBorder)
        GL11.glPopMatrix()
    }

    private fun drawBG() {
        GL11.glPushMatrix()
        GL11.glTranslated(x, y, 0.0)
        GL11.glScaled(width, height, 1.0)
        drawRect(ColorHelper.GuiDefaultBG)
        GL11.glPopMatrix()
    }

    private fun drawHL() {
        val t1 = GuiManager.DefaultConfig.BORDER_THICKNESS
        val t2 = GuiManager.DefaultConfig.HL_THICKNESS
        GL11.glPushMatrix()
        GL11.glTranslated(x - t1 - t2, y - t1 - t2, 0.0)
        GL11.glScaled(width + 2 * (t1 + t2), height + 2 * (t1 + t2), 1.0)
        drawRect(ColorHelper.GuiDefaultHL)
        GL11.glPopMatrix()
    }

    private fun drawRect(col : ColorHelper) {
        val worldRenderer = Tessellator.getInstance().worldRenderer
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)
        worldRenderer.pos(0.0, 0.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(0.0, 1.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(1.0, 1.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(1.0, 0.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        Tessellator.getInstance().draw()
    }

    fun mouseClickedEvent(mouseX : Double, mouseY : Double, button : Int) : GuiBase? {
        if(!(x <= mouseX && mouseX <= x + width))
            return null

        if(!(y <= mouseY && mouseY <= y + height))
            return null

        var clickedGui : GuiBase? = this
        for(child in children) {
            val gui = child.mouseClickedEvent(mouseX, mouseY, button)
            if(gui != null) {
                clickedGui = gui
                break
            }
        }

        return clickedGui
    }

    fun mouseAction(mouseX : Double, mouseY : Double, button : Int) {
        if(button == 0)
            ChatHelper.send("Received LEFT click at $this")
        else
            ChatHelper.send("Received RIGHT click at $this")
    }
}
