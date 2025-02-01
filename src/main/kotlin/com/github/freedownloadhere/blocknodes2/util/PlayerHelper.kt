package com.github.freedownloadhere.blocknodes2.util

import com.github.freedownloadhere.blocknodes2.mixin.AccessorMinecraft
import net.minecraft.client.Minecraft
import net.minecraft.util.Vec3

object PlayerHelper {
    fun getPlayerPosForRendering() : Vec3 {
        val mc = (Minecraft.getMinecraft() as AccessorMinecraft)
        val player = Minecraft.getMinecraft().thePlayer
        val partialTicks = mc.timer_blocknodes2.renderPartialTicks.toDouble()
        with(player) {
            return Vec3(
                prevPosX + (posX - prevPosX) * partialTicks,
                prevPosY + (posY - prevPosY) * partialTicks,
                prevPosZ + (posZ - prevPosZ) * partialTicks
            )
        }
    }
}