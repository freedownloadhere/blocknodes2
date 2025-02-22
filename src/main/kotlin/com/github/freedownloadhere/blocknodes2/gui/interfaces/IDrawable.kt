package com.github.freedownloadhere.blocknodes2.gui.interfaces

import com.github.freedownloadhere.blocknodes2.util.ColorHelper

interface IDrawable {
    var baseColor : ColorHelper
    fun draw()
}