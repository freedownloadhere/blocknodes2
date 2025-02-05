package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

class FdhGui(
    var x : Double,
    var y : Double,
    var width : Double,
    var height : Double
) {
    val focused = true
    val children = mutableListOf<FdhGui>()

    fun draw() {
        GlStateManager.translate(x, y, 0.0)

        if(focused)
            drawDefaultHL()

        drawDefaultBg()

        for(child in children)
            child.draw()
    }

    private fun drawDefaultBg() {
        GlStateManager.pushMatrix()
        GlStateManager.scale(width, height, 1.0)
        drawRect(Gui.Config.bgColor)
        GlStateManager.popMatrix()
    }

    private fun drawDefaultHL() {
        val t = Gui.Config.hlThickness
        val hc = Gui.Config.hlColor

        GlStateManager.pushMatrix()
        GlStateManager.translate(-t, -t, 0.0)
        GlStateManager.scale(width + 2 * t, height + 2 * t, 1.0)
        drawRect(hc)
        GlStateManager.popMatrix()
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
}
