package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.controls.ViewAngleHelper
import net.minecraft.util.Vec3

class NodeActionLookAt(private val pos : Vec3) : NodeAction() {
    override fun toString(): String
        = "Look At $pos"

    override fun execute() {
        super.execute()
        val va = ViewAngleHelper.getDeltaVA(pos)
        ViewAngleHelper.addVA(va)
    }
}