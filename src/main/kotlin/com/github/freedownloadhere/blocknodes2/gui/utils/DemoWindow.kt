package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.util.ChatHelper

class DemoWindow {
    private fun testParagraph() : String { var str = ""; for(i in 1..100) str += "Lorem Ipsum Dolor Sit Amet "; return str }

    val base =
        WindowBuilder("Demo Window Test")
            .newHeader("This is the window list")
            .newButton("A test button") { ChatHelper.send("A test button was pressed") }
            .beginList()
                .newHeader("This is a sublist")
                .newParagraph(testParagraph())
                .beginList()
                    .newHeader("Another sublist of the sublist")
                    .newButton("Another test button") { ChatHelper.send("A test button was pressed") }
                    .newParagraph(testParagraph())
                    .newButton("yet another test button") { ChatHelper.send("A test button was pressed") }
                .endList()
                .newParagraph(testParagraph())
            .endList()
            .beginList()
                .newHeader("Second sublist")
                .newParagraph(testParagraph())
                .beginList()
                    .newHeader("Another sublist of the sublist")
                    .newParagraph(testParagraph())
                .endList()
                .newParagraph(testParagraph())
            .endList()
            .finish()
}