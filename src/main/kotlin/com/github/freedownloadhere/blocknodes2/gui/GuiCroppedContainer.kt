package com.github.freedownloadhere.blocknodes2.gui

import org.lwjgl.opengl.GL11

open class GuiCroppedContainer(width : Int, height : Int) : GuiList() {
    init {
        w = width.toDouble()
        h = height.toDouble()
        flagList.add(Flags.ListStaticSize)
    }

    override fun update(deltaTime: Long) {
        val thickness = GuiManager.DefaultConfig.BORDER_THICKNESS.toInt()
        GL11.glEnable(GL11.GL_SCISSOR_TEST)
        GL11.glScissor(
            x.toInt() - thickness,
            GuiManager.height - y.toInt() - h.toInt() - thickness,
            w.toInt() + 2 * thickness,
            h.toInt() + 2 * thickness
        )
        super.update(deltaTime)
        GL11.glDisable(GL11.GL_SCISSOR_TEST)
    }
}