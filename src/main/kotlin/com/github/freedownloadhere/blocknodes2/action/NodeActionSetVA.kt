package com.github.freedownloadhere.blocknodes2.action

import com.github.freedownloadhere.blocknodes2.controls.ViewAngleHelper
import com.github.freedownloadhere.blocknodes2.util.ViewAngles

class NodeActionSetVA(private val viewAngles : ViewAngles) : NodeAction() {
    override fun toString(): String
        = "Set View Angles $viewAngles"

    override fun execute() {
        super.execute()
        ViewAngleHelper.setVA(viewAngles)
    }
}