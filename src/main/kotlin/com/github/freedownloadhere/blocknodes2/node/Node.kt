package com.github.freedownloadhere.blocknodes2.node

import com.github.freedownloadhere.blocknodes2.render.RenderHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.entity.Entity
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.BlockPos
import net.minecraft.util.Vec3

class Node(
    private val pos : BlockPos
) {
    fun intersects(e : Entity) : Boolean {
        val aabb1 = e.entityBoundingBox
        val aabb2 = AxisAlignedBB(pos, pos.add(1, 1, 1))
        return aabb1.intersectsWith(aabb2)
    }

    fun render(observerPos : Vec3) {
        RenderHelper.highlightBegin()
        RenderHelper.useAbsolutePos(observerPos)
        RenderHelper.drawCube(pos, ColorHelper.TranslucentCyan.toColorObj())
        RenderHelper.highlightEnd()
    }
}