package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

abstract class Gui(
    internal var x : Double,
    internal var y : Double,
    internal var w : Double,
    internal var h : Double
) {
    private var toggled = true

    enum class Direction {
        Left, Right, Top, Bottom
    }

    open fun toggle() : Gui {
        toggled = !toggled
        return this
    }

    open fun finish() : Gui {
        return this
    }

    open fun setXY(newX : Double, newY : Double) {
        moveBy(newX - x, newY - y)
    }

    open fun setWH(newW : Double, newH : Double) {
        scaleBy(newW / w, newH / h)
    }

    open fun moveBy(dx : Double, dy : Double) {
        x += dx
        y += dy
    }

    open fun scaleBy(wMult : Double, hMult : Double) {
        w *= wMult
        h *= hMult

        x *= wMult
        y *= hMult
    }

    open fun scaleBy(mult : Double) {
        scaleBy(mult, mult)
    }

    open fun center(xCenter : Double, yCenter : Double) {
        val dw = w / 2.0
        val dh = h / 2.0
        setXY(xCenter - dw, yCenter - dh)
    }

    open fun centerIn(parent : Gui) {
        val xCenter = parent.x + parent.w / 2.0
        val yCenter = parent.y + parent.h / 2.0
        center(xCenter, yCenter)
    }

    open fun snapTo(parent : Gui, type : Direction) {
        when(type) {
            Direction.Left -> setXY(parent.x, y)
            Direction.Right -> setXY(parent.x + parent.w - w, y)
            Direction.Top -> setXY(x, parent.y)
            Direction.Bottom -> setXY(x, parent.y + parent.h - h)
        }
    }

    open fun update(deltaTime : Long) {
        if(!toggled) return
        draw()
    }

    protected open fun draw() {
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()

        drawBG()
    }

    protected fun drawBorder(col : ColorHelper = ColorHelper.GuiBorder) {
        val t = GuiManager.DefaultConfig.BORDER_THICKNESS
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPushMatrix()
        GL11.glTranslated(x - t, y - t, 0.0)
        GL11.glScaled(w + 2 * t, h + 2 * t, 1.0)
        drawRect(col)
        GL11.glPopMatrix()
    }

    protected fun drawBG(col : ColorHelper = ColorHelper.GuiBGDark) {
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPushMatrix()
        GL11.glTranslated(x, y, 0.0)
        GL11.glScaled(w, h, 1.0)
        drawRect(col)
        GL11.glPopMatrix()
    }

    protected fun drawHL() {
        val t1 = GuiManager.DefaultConfig.BORDER_THICKNESS
        val t2 = GuiManager.DefaultConfig.HL_THICKNESS
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glPushMatrix()
        GL11.glTranslated(x - t1 - t2, y - t1 - t2, 0.0)
        GL11.glScaled(w + 2 * (t1 + t2), h + 2 * (t1 + t2), 1.0)
        drawRect(ColorHelper.GuiHL)
        GL11.glPopMatrix()
    }

    private fun drawRect(col : ColorHelper) {
        val worldRenderer = Tessellator.getInstance().worldRenderer
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)
        worldRenderer.pos(0.0, 0.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(0.0, 1.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(1.0, 1.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        worldRenderer.pos(1.0, 0.0, 0.0).color(col.r, col.g, col.b, col.a).endVertex()
        Tessellator.getInstance().draw()
    }
}