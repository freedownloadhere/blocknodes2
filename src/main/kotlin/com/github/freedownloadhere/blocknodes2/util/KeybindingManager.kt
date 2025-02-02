package com.github.freedownloadhere.blocknodes2.util

import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent

object KeybindingManager {
    private val keyToCallback = mutableMapOf<KeyBinding, () -> Unit>()

    fun addKey(key : KeyBinding, callback : () -> Unit) {
        keyToCallback[key] = callback
        ClientRegistry.registerKeyBinding(key)
    }

    @SubscribeEvent
    fun update(e : InputEvent.KeyInputEvent) {
        for((key, callback) in keyToCallback)
            if(key.isPressed)
                callback()
    }
}