package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11

object Gui {
    private val root = FdhGui(20.0, 30.0, 200.0, 100.0)

    init {
        root.children.add(FdhGui(10.0, 10.0, 50.0, 50.0))
    }

    object Config {
        val hlThickness = 1.0
        val hlColor = ColorHelper.DefaultPurple
        val bgColor = ColorHelper.DefaultGray
    }

    fun draw() {
        GlStateManager.pushAttrib()
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_LIGHTING)
        //not working as expected?
        //GlStateManager.disableTexture2D()
        //GlStateManager.disableLighting()
        GlStateManager.pushMatrix()
        root.draw()
        GlStateManager.popMatrix()
        GlStateManager.popAttrib()
    }
}