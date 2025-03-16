package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.gui.GuiListContainer
import net.minecraft.client.Minecraft

object TextUtils {
    fun wordWrap(str : String, parent : Gui, scaleMult : Double = 1.0) : List<String> {
        val fr = Minecraft.getMinecraft().fontRendererObj
        val ts = scaleMult * Manager.config.textScale
        val strList = mutableListOf<String>()
        val rect = LayoutUtils.Rectangle(parent)
        if(parent is GuiListContainer)
            rect.scale(1.0 - 2.0 * Manager.config.listSpacingScale)

        var width = 0.0
        val buffer = StringBuilder()
        for(c in str) {
            val chw = fr.getCharWidth(c) * ts
            if(width + chw > rect.w) {
                width = 0.0
                val newRow = buffer.toString()
                strList.add(newRow)
                buffer.clear()
            }
            buffer.append(c)
            width += fr.getCharWidth(c) * ts
        }

        if(buffer.isNotBlank()) {
            val newRow = buffer.toString()
            strList.add(newRow)
        }

        return strList
    }
}