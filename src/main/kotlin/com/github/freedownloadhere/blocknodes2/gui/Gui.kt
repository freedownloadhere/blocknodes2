package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

abstract class Gui {
    internal var x = 0.0
    internal var y = 0.0
    internal var w = 1.0
    internal var h = 1.0
    internal val children = mutableListOf<Gui>()
    var toggled = true
        private set
    private var initialized = false

    enum class SnapDir { Left, Right, Top, Bottom }

    open fun postInit() { }

    open fun toggle() {
        toggled = !toggled
        if(toggled)
            for(child in children)
                child.enable()
        else
            for(child in children)
                child.disable()
    }

    open fun addChild(child : Gui) {
        if(!child.initialized)
            child.postInit()
        children.add(child)
    }

    open fun enable() {
        toggled = true
        for(child in children)
            child.enable()
    }

    open fun disable() {
        toggled = false
        for(child in children)
            child.disable()
    }

    open fun translate(dx : Double, dy : Double) {
        x += dx
        y += dy
        for(child in children)
            child.translate(dx, dy)
    }

    open fun translateSetXY(newX : Double, newY : Double) {
        translate(newX - x, newY - y)
    }

    open fun translatePlaceRelativeTo(parent : Gui) {
        translate(parent.x, parent.y)
    }

    open fun translateCenter(xCenter : Double, yCenter : Double) {
        val dw = w / 2.0
        val dh = h / 2.0
        translateSetXY(xCenter - dw, yCenter - dh)
    }

    open fun translateCenterIn(parent : Gui) {
        val xCenter = parent.x + parent.w / 2.0
        val yCenter = parent.y + parent.h / 2.0
        translateCenter(xCenter, yCenter)
    }

    open fun translateSnapTo(parent : Gui, type : SnapDir, padding : Double = 0.0) {
        when(type) {
            SnapDir.Left -> translateSetXY(parent.x + padding, y)
            SnapDir.Right -> translateSetXY(parent.x + parent.w - w - padding, y)
            SnapDir.Top -> translateSetXY(x, parent.y + padding)
            SnapDir.Bottom -> translateSetXY(x, parent.y + parent.h - h - padding)
        }
    }

    open fun scale(wMult : Double, hMult : Double) {
        w *= wMult
        h *= hMult

        x *= wMult
        y *= hMult

        for(child in children)
            child.scale(wMult, hMult)
    }

    open fun scale(mult : Double) {
        scale(mult, mult)
    }

    open fun scaleSetWH(newW : Double, newH : Double) {
        scale(newW / w, newH / h)
    }

    open fun scaleExpandIn(parent : Gui) {
        if(h * (parent.w / w) <= parent.h)
            scale(parent.w / w)
        else
            scale(parent.h / h)
    }

    open fun update(deltaTime : Long) {
        if(!toggled) return
        draw()
        for(child in children)
            child.update(deltaTime)
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
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(x - t, y - t, 0.0)
        GlStateManager.scale(w + 2 * t, h + 2 * t, 1.0)
        drawRect(col)
        GlStateManager.popMatrix()
    }

    protected fun drawBG(col : ColorHelper = ColorHelper.GuiBGDark) {
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(x, y, 0.0)
        GlStateManager.scale(w, h, 1.0)
        drawRect(col)
        GlStateManager.popMatrix()
    }

    protected fun drawHL() {
        val t1 = GuiManager.DefaultConfig.BORDER_THICKNESS
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(x - t1, y - t1, 0.0)
        GlStateManager.scale(w + 2 * t1, h + 2 * t1, 1.0)
        drawRect(ColorHelper.GuiHL)
        GlStateManager.popMatrix()
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