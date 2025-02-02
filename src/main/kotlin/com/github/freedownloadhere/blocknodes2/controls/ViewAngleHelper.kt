package com.github.freedownloadhere.blocknodes2.controls

import com.github.freedownloadhere.blocknodes2.util.PlayerHelper
import com.github.freedownloadhere.blocknodes2.util.ViewAngles
import com.github.freedownloadhere.blocknodes2.util.eClampAngle180
import com.github.freedownloadhere.blocknodes2.util.eToDegrees
import net.minecraft.client.Minecraft
import net.minecraft.util.Vec3
import kotlin.math.atan2
import kotlin.math.hypot

object ViewAngleHelper {
    fun getDeltaVA(target : Vec3) : ViewAngles {
        val start = PlayerHelper.eyePos()
        val distance = target.subtract(start)
        val posYaw = -atan2(distance.xCoord, distance.zCoord).toFloat().eToDegrees().eClampAngle180()
        val posPitch = -atan2(distance.yCoord, hypot(distance.xCoord, distance.zCoord)).toFloat().eToDegrees()
        val deltaYaw = (posYaw - Minecraft.getMinecraft().thePlayer.rotationYaw).eClampAngle180()
        val deltaPitch = (posPitch - Minecraft.getMinecraft().thePlayer.rotationPitch)
        return ViewAngles(deltaYaw, deltaPitch)
    }

    fun addVA(viewAngles: ViewAngles) {
        val player = Minecraft.getMinecraft().thePlayer
        player.rotationYaw += viewAngles.yaw
        player.rotationPitch += viewAngles.pitch
    }

    fun setVA(viewAngles: ViewAngles) {
        val player = Minecraft.getMinecraft().thePlayer
        player.rotationYaw = viewAngles.yaw
        player.rotationPitch = viewAngles.pitch
    }
}