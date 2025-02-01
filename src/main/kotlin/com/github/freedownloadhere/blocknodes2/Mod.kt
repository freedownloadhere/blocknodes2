package com.github.freedownloadhere.blocknodes2

import com.github.freedownloadhere.blocknodes2.node.Node
import net.minecraft.client.Minecraft
import net.minecraft.util.BlockPos
import net.minecraftforge.client.event.DrawBlockHighlightEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = "blocknodes2", useMetadata = true)
class Mod {
    val testNode = Node(BlockPos(100, 100, 100))

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun renderOverlay(e : DrawBlockHighlightEvent) {
        testNode.render(Minecraft.getMinecraft().thePlayer.positionVector)
    }
}
