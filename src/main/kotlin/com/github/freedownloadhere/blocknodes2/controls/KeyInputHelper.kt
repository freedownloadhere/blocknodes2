package com.github.freedownloadhere.blocknodes2.controls

import net.minecraft.client.settings.KeyBinding

object KeyInputHelper {
    private val toHold = mutableSetOf<Int>()
    private val toTap = mutableSetOf<Int>()

    fun update() {
        for(key in toTap)
            KeyBinding.setKeyBindState(key, true)
        toTap.clear()

        for(key in toHold) {
            KeyBinding.onTick(key)
            KeyBinding.setKeyBindState(key, true)
        }
    }

    fun beginHolding(key : KeyBinding) {
        toHold.add(key.keyCode)
        KeyBinding.setKeyBindState(key.keyCode, true)
    }

    fun stopHolding(key : KeyBinding) {
        if(toHold.contains(key.keyCode)) {
            toHold.remove(key.keyCode)
            KeyBinding.setKeyBindState(key.keyCode, false)
        }
    }

    fun tap(key : KeyBinding) {
        toTap.add(key.keyCode)
    }
}