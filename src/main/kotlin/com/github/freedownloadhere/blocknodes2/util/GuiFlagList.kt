package com.github.freedownloadhere.blocknodes2.util

import com.github.freedownloadhere.blocknodes2.gui.Gui

class GuiFlagList {
    var flags = 0
        private set

    fun add(flag : Gui.Flags) {
        flags = flags or flag.v
    }

    fun remove(flag : Gui.Flags) {
        flags = flags and flag.v.inv()
    }

    fun toggle(flag : Gui.Flags) {
        flags = flags xor flag.v
    }

    fun isActive(flag : Gui.Flags) : Boolean {
        return (flags and flag.v) != 0
    }

    fun isNotActive(flag : Gui.Flags) : Boolean {
        return (flags and flag.v) == 0
    }
}