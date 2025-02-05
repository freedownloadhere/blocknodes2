package com.github.freedownloadhere.blocknodes2.util

import org.lwjgl.util.Color

enum class ColorHelper(val r : Int, val g : Int, val b : Int, val a : Int) {
    DefaultGray(50, 50, 50, 255),
    DefaultPurple(93, 43, 144, 255),
    TranslucentCyan(Color.CYAN.red, Color.CYAN.green, Color.CYAN.blue, 60),
    TranslucentRed(Color.RED.red, Color.RED.green, Color.RED.blue, 60);

    fun toColorObj() : Color = Color(r, g, b, a)
}