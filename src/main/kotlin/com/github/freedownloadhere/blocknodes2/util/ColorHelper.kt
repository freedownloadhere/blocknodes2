package com.github.freedownloadhere.blocknodes2.util

import org.lwjgl.util.Color

enum class ColorHelper(val r : Int, val g : Int, val b : Int, val a : Int) {
    TranslucentCyan(Color.CYAN.red, Color.CYAN.green, Color.CYAN.blue, 100),
    TranslucentRed(Color.RED.red, Color.RED.green, Color.RED.blue, 100);

    fun toColorObj() : Color = Color(r, g, b, a)
}