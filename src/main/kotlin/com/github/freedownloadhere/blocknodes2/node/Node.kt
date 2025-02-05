package com.github.freedownloadhere.blocknodes2.node

import com.github.freedownloadhere.blocknodes2.action.NodeAction
import com.github.freedownloadhere.blocknodes2.render.RenderHelper
import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import com.github.freedownloadhere.blocknodes2.util.PlayerHelper
import net.minecraft.client.Minecraft
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.BlockPos

class Node(
    private val pos : BlockPos,
    val actionList : List<NodeAction>
) {
    var contactTime = 0
        private set

    fun update() {
        val aabb1 = Minecraft.getMinecraft().thePlayer.entityBoundingBox
        val aabb2 = AxisAlignedBB(pos, pos.add(1, 1, 1))
        val intersect = aabb1.intersectsWith(aabb2)
        contactTime = if(intersect) contactTime + 1 else 0
    }

    fun render() {
        RenderHelper.highlightBegin()
        RenderHelper.useAbsolutePos(PlayerHelper.renderPos())
        val color =
            if(contactTime > 0) ColorHelper.NodeActive.toColorObj()
            else ColorHelper.NodeInactive.toColorObj()
        RenderHelper.drawCube(pos, color)
        RenderHelper.highlightEnd()
    }
}