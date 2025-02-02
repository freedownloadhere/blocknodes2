package com.github.freedownloadhere.blocknodes2.util

import com.github.freedownloadhere.blocknodes2.mixin.AccessorMinecraft
import net.minecraft.client.Minecraft
import net.minecraft.util.Vec3

object PlayerHelper {
    fun partialTicks() : Float
        = (Minecraft.getMinecraft() as AccessorMinecraft).timer_blocknodes2.renderPartialTicks

    fun renderPos() : Vec3 {
        val partialTicks = partialTicks()
        with(Minecraft.getMinecraft().thePlayer) {
            return Vec3(
                prevPosX + (posX - prevPosX) * partialTicks,
                prevPosY + (posY - prevPosY) * partialTicks,
                prevPosZ + (posZ - prevPosZ) * partialTicks
            )
        }
    }

    fun eyePos() : Vec3 {
        val pos = Minecraft.getMinecraft().thePlayer.positionVector
        val height = Vec3(0.0, Minecraft.getMinecraft().thePlayer.eyeHeight.toDouble(), 0.0)
        return pos.add(height)
    }
}