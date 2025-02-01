package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.util.ChatHelper
import net.minecraft.client.Minecraft

enum class Actions(private val callback : () -> Unit) {
    GoForward({
        KeyBindHelper.beginHolding(Minecraft.getMinecraft().gameSettings.keyBindForward)
    }),

    StopForward({
        KeyBindHelper.stopHolding(Minecraft.getMinecraft().gameSettings.keyBindForward)
    });

    fun execute() {
        ChatHelper.send(this.toString(), "Action")
        callback()
    }
}