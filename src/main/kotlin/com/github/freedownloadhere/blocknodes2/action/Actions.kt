package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.util.ChatHelper

enum class Actions(private val callback : () -> Unit) {
    GoForward({ ChatHelper.send("Going forward...") }),
    StopForward({ ChatHelper.send("Stopped going forward...") });

    fun execute() {
        callback()
    }
}