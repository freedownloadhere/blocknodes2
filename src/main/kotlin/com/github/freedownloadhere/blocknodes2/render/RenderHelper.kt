package com.github.freedownloadhere.blocknodes2.render

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
        GL11.glPushMatrix()
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT)
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDepthFunc(GL11.GL_ALWAYS)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
    }

    fun useAbsolutePos(observerPos : Vec3) {
        GL11.glTranslated(-observerPos.xCoord, -observerPos.yCoord, -observerPos.zCoord)
        val worldRenderer = Tessellator.getInstance().worldRenderer
    }

    fun highlightEnd() {
        GL11.glPopAttrib()
        GL11.glDepthFunc(GL11.GL_LEQUAL)
        GL11.glPopMatrix()
    }

    fun drawLine(pos1 : Vec3, pos2 : Vec3, color : Color) {
        val worldRenderer = Tessellator.getInstance().worldRenderer
        worldRenderer.begin(GL11.GL_LINE, DefaultVertexFormats.POSITION_COLOR)
        worldRenderer.pos(pos1.xCoord, pos1.yCoord, pos1.zCoord).color(color.red, color.green, color.blue, color.alpha).endVertex()
        worldRenderer.pos(pos2.xCoord, pos2.yCoord, pos2.zCoord).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Tessellator.getInstance().draw()
    }

    fun drawCube(pos : BlockPos, color : Color) {
        GL11.glTranslated(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
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
        GL11.glTranslated(-pos.x.toDouble(), -pos.y.toDouble(), -pos.z.toDouble())
    }
}