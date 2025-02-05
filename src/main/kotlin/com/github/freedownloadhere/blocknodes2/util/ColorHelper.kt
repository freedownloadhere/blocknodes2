package com.github.freedownloadhere.blocknodes2.util

import org.lwjgl.util.Color

enum class ColorHelper(val r : Int, val g : Int, val b : Int, val a : Int) {
    GuiDefaultBG(50, 50, 50, 255),
    GuiDefaultHL(93, 43, 144, 255),
    GuiDefaultBorder(90, 90, 90, 255),
    NodeInactive(Color.CYAN.red, Color.CYAN.green, Color.CYAN.blue, 60),
    NodeActive(Color.RED.red, Color.RED.green, Color.RED.blue, 60);

    fun toColorObj() : Color = Color(r, g, b, a)

    fun toPackedARGB() : Int = 0 or (a shl 24) or (r shl 16) or (g shl 8) or b
}