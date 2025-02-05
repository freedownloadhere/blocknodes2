package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

class GuiBase(
    private var x : Double,
    private var y : Double,
    private var width : Double,
    private var height : Double
) {
    val children = mutableListOf<GuiBase>()

    fun renderUpdate() {
        draw()
        for(child in children)
            child.draw()
    }

    private fun draw() {
        drawBorder()
        drawBG()
    }

    private fun drawBorder() {
        val t = GuiManager.DefaultConfig.borderThickness
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

    private fun drawRect(col : ColorHelper) {
        val worldRenderer = Tessellator.getInstance().worldRenderer
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)
        worldRenderer.pos(0.0, 0.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(0.0, 1.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(1.0, 1.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(1.0, 0.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        Tessellator.getInstance().draw()
    }

    fun mouseClickedEvent(mouseX : Double, mouseY : Double, button : Int) {
        if(x <= mouseX && mouseX <= x + width)
            if(y <= mouseY && mouseY <= y + height)
                mouseAction(mouseX, mouseY, button)

        for(child in children)
            child.mouseClickedEvent(mouseX, mouseY, button)
    }

    private fun mouseAction(mouseX : Double, mouseY : Double, button : Int) {
        if(button == 0)
            ChatHelper.send("Received LEFT click at $this")
        else
            ChatHelper.send("Received RIGHT click at $this")
    }
}
