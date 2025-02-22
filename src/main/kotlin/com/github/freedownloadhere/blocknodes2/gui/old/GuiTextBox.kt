package com.github.freedownloadhere.blocknodes2.gui.old

//class GuiTextBox(
//    private val contents : KMutableProperty0<String>,
//    private val placeholder : String = "Type something..."
//) : GuiList(), IGuiDrawable {
//    private val guiText = GuiText(placeholder)
//
//    init {
//        guiText.baseColor = ColorHelper.GuiNeutralLight
//        addChild(guiText)
//    }
//
//    override fun onKeyTyped(typedChar: Char, keyCode: Int) {
//        if(contents.get().isNotEmpty() && keyCode == Keyboard.KEY_BACK || keyCode == Keyboard.KEY_DELETE)
//            contents.set(contents.get().dropLast(1))
//
//        else if(!typedChar.isISOControl())
//            contents.set(contents.get() + typedChar)
//
//        if(contents.get().isEmpty()) {
//            guiText.baseColor = ColorHelper.GuiNeutralLight
//            guiText.updateText("$placeholder ")
//        }
//        else {
//            guiText.baseColor = ColorHelper.White
//            guiText.updateText("${contents.get()} ")
//        }
//    }
//
//    override fun update(deltaTime: Long) {
//        ScissorStack.push(this)
//        ScissorStack.apply()
//        super.update(deltaTime)
//        ScissorStack.pop()
//    }
//
//    override var baseColor = ColorHelper.GuiNeutralDark
//    override fun draw() {
//        GuiRenderer.drawBasicBG(this)
//    }
//}