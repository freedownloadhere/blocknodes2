package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.controls.KeyInputHelper
import net.minecraft.client.settings.KeyBinding

class NodeActionHoldKey(private val key : KeyBinding) : NodeAction() {
    override fun toString(): String
        = "Hold Key $key"

    override fun execute() {
        super.execute()
        KeyInputHelper.beginHolding(key)
    }
}