package com.github.freedownloadhere.blocknodes2

import com.github.freedownloadhere.blocknodes2.action.NodeActionHoldKey
import com.github.freedownloadhere.blocknodes2.action.NodeActionLookAt
import com.github.freedownloadhere.blocknodes2.action.NodeActionReleaseKey
import com.github.freedownloadhere.blocknodes2.controls.KeyInputHelper
import com.github.freedownloadhere.blocknodes2.gui.BlockNodesGui
import com.github.freedownloadhere.blocknodes2.gui.Gui
import com.github.freedownloadhere.blocknodes2.node.Node
import com.github.freedownloadhere.blocknodes2.node.NodeScene
import com.github.freedownloadhere.blocknodes2.util.KeybindingManager
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraft.util.BlockPos
import net.minecraft.util.Vec3
import net.minecraftforge.client.event.DrawBlockHighlightEvent
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard

@Mod(modid = "blocknodes2", useMetadata = true)
class Mod {
    private val testScene = NodeScene()

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(this)
        MinecraftForge.EVENT_BUS.register(KeybindingManager)
        KeybindingManager.addKey(KeyBinding("blocknodes2.togglegui", Keyboard.KEY_J, "blocknodes2.keys")) {
            Minecraft.getMinecraft().displayGuiScreen(BlockNodesGui(testScene))
        }
        testScene.nodeList.add(Node(BlockPos(0, 4, 0), listOf(NodeActionHoldKey(Minecraft.getMinecraft().gameSettings.keyBindForward))))
        testScene.nodeList.add(Node(BlockPos(10, 4, 0), listOf(NodeActionReleaseKey(Minecraft.getMinecraft().gameSettings.keyBindForward))))
        testScene.nodeList.add(Node(BlockPos(3, 4, 3), listOf(NodeActionLookAt(Vec3(0.0, 3.0, 0.0)))))
    }

    @SubscribeEvent
    fun highlightBlock(e : DrawBlockHighlightEvent) {
        if(Minecraft.getMinecraft().thePlayer == null) return
        if(Minecraft.getMinecraft().theWorld == null) return

        testScene.render()
    }

    @SubscribeEvent
    fun renderOverlay(e : RenderGameOverlayEvent.Post) {
        Gui.draw()
    }

    @SubscribeEvent
    fun onTick(e : TickEvent.ClientTickEvent) {
        if(e.phase != TickEvent.Phase.END) return
        if(Minecraft.getMinecraft().thePlayer == null) return
        if(Minecraft.getMinecraft().theWorld == null) return

        testScene.update()
        KeyInputHelper.update()
    }
}
