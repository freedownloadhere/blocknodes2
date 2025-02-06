package com.github.freedownloadhere.blocknodes2.util

import org.lwjgl.util.Color

enum class ColorHelper(val r : Int, val g : Int, val b : Int, val a : Int) {
    White(255, 255, 255, 255),
    GuiBGDarkest(30, 30, 30, 255),
    GuiBGDark(50, 50, 50, 255),
    GuiBGLight(70, 70, 70, 255),
    GuiBorder(90, 90, 90, 255),
    GuiHL(93, 43, 144, 255),
    NodeInactive(53, 193, 225, 60),
    NodeActive(225, 53, 107, 60);

    fun toColorObj() : Color = Color(r, g, b, a)

    fun toPackedARGB() : Int = 0 or (a shl 24) or (r shl 16) or (g shl 8) or b
}