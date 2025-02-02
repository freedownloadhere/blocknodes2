package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.util.ChatHelper

abstract class NodeAction {
    open fun execute() {
        ChatHelper.send(toString(), "Action")
    }
}