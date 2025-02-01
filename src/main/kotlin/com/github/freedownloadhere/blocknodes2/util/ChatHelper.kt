package com.github.freedownloadhere.blocknodes2.util

import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText

object ChatHelper {
    fun send(message : String, prefix : String = "BlockNodesII") {
        Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(
            "\u00A7l[\u00A73\u00A7l$prefix\u00A7f\u00A7l]\u00A77 $message"
        ))
    }
}