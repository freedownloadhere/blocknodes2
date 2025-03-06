package com.github.freedownloadhere.blocknodes2.gui.utils

import kotlin.test.assertEquals

class LayoutUtilsTest {
    fun rectangleScaleByTwo() {
        val expected = arrayOf(
            LayoutUtils.Rectangle(),
            LayoutUtils.Rectangle(x1 = -20.0, x2 = 20.0),
            LayoutUtils.Rectangle(y1 = -20.0, y2 = 20.0),
            LayoutUtils.Rectangle(x1 = -20.0, y1 = -20.0, x2 = 20.0, y2 = 20.0)
        )

        val actual = arrayOf(
            LayoutUtils.Rectangle().scale(2.0),
            LayoutUtils.Rectangle(x1 = -10.0, x2 = 10.0).scale(2.0),
            LayoutUtils.Rectangle(y1 = -10.0, y2 = 10.0).scale(2.0),
            LayoutUtils.Rectangle(x1 = -10.0, y1 = -10.0, x2 = 10.0, y2 = 10.0).scale(2.0)
        )

        for(i in 0..3)
            assertEquals(expected[i], actual[i])
    }
}