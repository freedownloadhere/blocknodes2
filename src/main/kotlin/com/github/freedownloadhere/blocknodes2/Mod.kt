package com.github.freedownloadhere.blocknodes2

import com.github.freedownloadhere.blocknodes2.action.NodeActionHoldKey
import com.github.freedownloadhere.blocknodes2.action.NodeActionLookAt
import com.github.freedownloadhere.blocknodes2.action.NodeActionReleaseKey
import com.github.freedownloadhere.blocknodes2.controls.KeyInputHelper
import com.github.freedownloadhere.blocknodes2.gui.GuiManager
import com.github.freedownloadhere.blocknodes2.node.Node
import com.github.freedownloadhere.blocknodes2.node.NodeSceneManager
import com.github.freedownloadhere.blocknodes2.util.KeybindingManager
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraft.util.BlockPos
import net.minecraft.util.Vec3
import net.minecraftforge.client.event.DrawBlockHighlightEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard

@Mod(modid = "blocknodes2", useMetadata = true)
class Mod {
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(this)
        MinecraftForge.EVENT_BUS.register(KeybindingManager)
        KeybindingManager.addKey(KeyBinding("blocknodes2.togglegui", Keyboard.KEY_J, "blocknodes2.keys")) {
            Minecraft.getMinecraft().displayGuiScreen(GuiManager)
        }
    }

    @SubscribeEvent
    fun highlightBlock(e : DrawBlockHighlightEvent) {
        if(Minecraft.getMinecraft().thePlayer == null) return
        if(Minecraft.getMinecraft().theWorld == null) return

        NodeSceneManager.loadedScene?.render()
    }

    @SubscribeEvent
    fun onTick(e : TickEvent.ClientTickEvent) {
        if(e.phase != TickEvent.Phase.END) return
        if(Minecraft.getMinecraft().thePlayer == null) return
        if(Minecraft.getMinecraft().theWorld == null) return

        NodeSceneManager.loadedScene?.update()
        KeyInputHelper.update()
    }
}
