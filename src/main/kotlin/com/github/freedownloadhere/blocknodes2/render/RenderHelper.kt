package com.github.freedownloadhere.blocknodes2.render

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.BlockPos
import net.minecraft.util.Vec3
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Color

object RenderHelper {
    private val cubeVertices = arrayOf(
        Vec3(0.0, 0.0, 0.0),
        Vec3(1.0, 0.0, 0.0),
        Vec3(1.0, 0.0, 1.0),
        Vec3(0.0, 0.0, 1.0),
        Vec3(0.0, 1.0, 0.0),
        Vec3(1.0, 1.0, 0.0),
        Vec3(1.0, 1.0, 1.0),
        Vec3(0.0, 1.0, 1.0)
    )

    data class Index(val i1 : Int, val i2 : Int, val i3 : Int, val i4 : Int)
    private val cubeIndices = arrayOf(
        Index(0, 1, 2, 3),
        Index(4, 5, 6, 7),
        Index(0, 1, 5, 4),
        Index(1, 2, 6, 5),
        Index(2, 3, 7, 6),
        Index(3, 0, 4, 7)
    )

    fun highlightBegin() {
        GlStateManager.pushMatrix()
        GlStateManager.disableLighting()
        GlStateManager.disableTexture2D()
        GlStateManager.depthFunc(GL11.GL_ALWAYS)
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
    }

    fun useAbsolutePos(observerPos : Vec3) {
        GlStateManager.translate(-observerPos.xCoord, -observerPos.yCoord, -observerPos.zCoord)
    }

    fun highlightEnd() {
        GlStateManager.depthFunc(GL11.GL_LEQUAL)
        GlStateManager.enableTexture2D()
        GlStateManager.enableLighting()
        GlStateManager.popMatrix()
    }

    fun drawCube(pos : BlockPos, color : Color) {
        GlStateManager.pushMatrix()
        GlStateManager.translate(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())

        val tess = Tessellator.getInstance()
        val worldrenderer = tess.worldRenderer
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)

        for(quad in cubeIndices) {
            val p1 = cubeVertices[quad.i1]
            val p2 = cubeVertices[quad.i2]
            val p3 = cubeVertices[quad.i3]
            val p4 = cubeVertices[quad.i4]
            worldrenderer.pos(p1.xCoord, p1.yCoord, p1.zCoord).color(color.red, color.green, color.blue, color.alpha).endVertex()
            worldrenderer.pos(p2.xCoord, p2.yCoord, p2.zCoord).color(color.red, color.green, color.blue, color.alpha).endVertex()
            worldrenderer.pos(p3.xCoord, p3.yCoord, p3.zCoord).color(color.red, color.green, color.blue, color.alpha).endVertex()
            worldrenderer.pos(p4.xCoord, p4.yCoord, p4.zCoord).color(color.red, color.green, color.blue, color.alpha).endVertex()
        }
        tess.draw()

        GlStateManager.popMatrix()
    }
}