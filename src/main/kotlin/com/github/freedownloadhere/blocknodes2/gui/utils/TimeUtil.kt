package com.github.freedownloadhere.blocknodes2.gui.utils

import java.time.Instant

class TimeUtil {
    private var lastTime = Instant.now().toEpochMilli()
    fun newDeltaTime() : Long {
        val currTime = Instant.now().toEpochMilli()
        val deltaTime = currTime - lastTime
        lastTime = currTime
        return deltaTime
    }
}