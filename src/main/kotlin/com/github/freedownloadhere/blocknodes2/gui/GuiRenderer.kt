package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.gui.Gui.Flags
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

object GuiRenderer {
    fun drawBasicBG(gui : Gui) {
        if(gui.flagList.isActive(Flags.TransparentBG))
            return
        if(gui == GuiManager.focused)
            drawHL(gui)
        else
            drawBorder(gui)
        drawBG(gui, gui.bgColor)
    }

    fun drawBorder(gui : Gui, col : ColorHelper = ColorHelper.GuiNeutralLight) {
        val t = GuiManager.DefaultConfig.BORDER_THICKNESS
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(gui.x - t, gui.y - t, 0.0)
        GlStateManager.scale(gui.w + 2 * t, gui.h + 2 * t, 1.0)
        drawRect(col)
        GlStateManager.popMatrix()
    }

    fun drawBG(gui : Gui, col : ColorHelper = ColorHelper.GuiNeutral) {
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(gui.x, gui.y, 0.0)
        GlStateManager.scale(gui.w, gui.h, 1.0)
        drawRect(col)
        GlStateManager.popMatrix()
    }

    fun drawHL(gui : Gui) {
        val t1 = GuiManager.DefaultConfig.BORDER_THICKNESS
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(gui.x - t1, gui.y - t1, 0.0)
        GlStateManager.scale(gui.w + 2 * t1, gui.h + 2 * t1, 1.0)
        drawRect(ColorHelper.GuiPrimary)
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