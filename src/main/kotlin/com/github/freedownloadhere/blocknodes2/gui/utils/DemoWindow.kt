package com.github.freedownloadhere.blocknodes2.gui.utils

import com.github.freedownloadhere.blocknodes2.util.ChatHelper

class DemoWindow {
    private fun testParagraph() : String { var str = ""; for(i in 1..100) str += "Lorem Ipsum Dolor Sit Amet "; return str }

    val base =
        WindowBuilder("Demo Window Test")
            .header("This is the window list")
            .button("A test button") { ChatHelper.send("A test button was pressed") }
            .textBox("This is a textbox")
            .beginList()
                .header("This is a sublist")
                .paragraph(testParagraph())
                .beginList()
                    .textBox("This is a textbox")
                    .header("Another sublist of the sublist")
                    .button("Another test button") { ChatHelper.send("A test button was pressed") }
                    .paragraph(testParagraph())
                    .button("yet another test button") { ChatHelper.send("A test button was pressed") }
                .endList()
                .paragraph(testParagraph())
            .endList()
            .beginList()
                .header("Second sublist")
                .paragraph(testParagraph())
                .beginList()
                    .header("Another sublist of the sublist")
                    .paragraph(testParagraph())
                    .textBox("This is a textbox")
                .endList()
                .paragraph(testParagraph())
            .endList()
            .beginList()
            .header("This is a sublist")
            .paragraph(testParagraph())
            .beginList()
            .textBox("This is a textbox")
            .header("Another sublist of the sublist")
            .button("Another test button") { ChatHelper.send("A test button was pressed") }
            .paragraph(testParagraph())
            .button("yet another test button") { ChatHelper.send("A test button was pressed") }
            .endList()
            .paragraph(testParagraph())
            .endList()
            .textBox("This is a textbox")
            .finish()
}