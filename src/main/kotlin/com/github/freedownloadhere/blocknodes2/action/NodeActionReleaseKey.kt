package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.controls.KeyInputHelper
import net.minecraft.client.settings.KeyBinding

class NodeActionReleaseKey(private val key : KeyBinding) : NodeAction() {
    override fun toString(): String
        = "Release Key $key"

    override fun execute() {
        super.execute()
        KeyInputHelper.stopHolding(key)
    }
}