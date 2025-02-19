package com.github.freedownloadhere.blocknodes2.gui

import com.github.freedownloadhere.blocknodes2.util.ColorHelper
import com.github.freedownloadhere.blocknodes2.util.GuiFlagList
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11

abstract class Gui {
    internal var x = 0.0
    internal var y = 0.0
    internal var w = 0.0
    internal var h = 0.0
    internal val children = mutableListOf<Gui>()
    var toggled = true
        private set
    var bgColor = ColorHelper.GuiNeutral
    val flagList = GuiFlagList()

    enum class Flags(val v : Int) {
        TransparentBG(1 shl 0),
        ListHomogenousWidths(1 shl 1),
        ListStaticWidth(1 shl 2),
        ListStaticHeight(1 shl 3),
        ListStaticSize(ListStaticWidth.v or ListStaticHeight.v),

        // specifically the parent. doesnt affect children
        ShouldNotInteract(1 shl 4)
    }

    open fun toggle() {
        toggled = !toggled
        if(toggled)
            for(child in children)
                child.enable()
        else
            for(child in children)
                child.disable()
    }

    protected open fun addChild(child : Gui) : Gui {
        children.add(child)
        return child
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

    open fun setSize(newW : Double, newH : Double) {
        w = newW
        h = newH
    }

    open fun translate(dx : Double, dy : Double) {
        x += dx
        y += dy
        for(child in children)
            child.translate(dx, dy)
    }

    open fun setPosition(newX : Double, newY : Double) {
        translate(newX - x, newY - y)
    }

    open fun translatePlaceRelativeTo(parent : Gui) {
        translate(parent.x, parent.y)
    }

    open fun translateCenter(xCenter : Double, yCenter : Double) {
        val dw = w / 2.0
        val dh = h / 2.0
        setPosition(xCenter - dw, yCenter - dh)
    }

    open fun translateCenterIn(parent : Gui) {
        val xCenter = parent.x + parent.w / 2.0
        val yCenter = parent.y + parent.h / 2.0
        translateCenter(xCenter, yCenter)
    }

    enum class SnapDir { Left, Right, Top, Bottom }
    open fun translateSnapTo(parent : Gui, type : SnapDir, padding : Double = 0.0) {
        when(type) {
            SnapDir.Left -> setPosition(parent.x + padding, y)
            SnapDir.Right -> setPosition(parent.x + parent.w - w - padding, y)
            SnapDir.Top -> setPosition(x, parent.y + padding)
            SnapDir.Bottom -> setPosition(x, parent.y + parent.h - h - padding)
        }
    }

    open fun scale(wMult : Double, hMult : Double) {
        w *= wMult
        h *= hMult

        for(child in children)
            child.scale(wMult, hMult)
    }

    open fun scale(mult : Double) {
        scale(mult, mult)
    }

    open fun update(deltaTime : Long) {
        if(!toggled) return
        draw()
        for(child in children)
            child.update(deltaTime)
    }

    protected open fun draw() {
        if(flagList.isActive(Flags.TransparentBG))
            return
        if(this == GuiManager.focused)
            drawHL()
        else
            drawBorder()
        drawBG(bgColor)
    }

    protected fun drawBorder(col : ColorHelper = ColorHelper.GuiNeutralLight) {
        val t = GuiManager.DefaultConfig.BORDER_THICKNESS
        GlStateManager.matrixMode(GL11.GL_MODELVIEW)
        GlStateManager.pushMatrix()
        GlStateManager.translate(x - t, y - t, 0.0)
        GlStateManager.scale(w + 2 * t, h + 2 * t, 1.0)
        drawRect(col)
        GlStateManager.popMatrix()
    }

    protected fun drawBG(col : ColorHelper = ColorHelper.GuiNeutral) {
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
        drawRect(ColorHelper.GuiPrimary)
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