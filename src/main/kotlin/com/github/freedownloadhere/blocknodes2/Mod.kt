package com.github.freedownloadhere.blocknodes2

import com.github.freedownloadhere.blocknodes2.action.Actions
import com.github.freedownloadhere.blocknodes2.node.Node
import com.github.freedownloadhere.blocknodes2.node.NodeScene
import net.minecraft.client.Minecraft
import net.minecraft.util.BlockPos
import net.minecraftforge.client.event.DrawBlockHighlightEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = "blocknodes2", useMetadata = true)
class Mod {
    private val testScene = NodeScene()

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(this)
        testScene.nodeList.add(Node(BlockPos(0, 100, 0), listOf(Actions.GoForward)))
        testScene.nodeList.add(Node(BlockPos(1, 100, 0), listOf(Actions.StopForward)))
        testScene.nodeList.add(Node(BlockPos(3, 100, 3), listOf(Actions.GoForward)))
    }

    @SubscribeEvent
    fun renderOverlay(e : DrawBlockHighlightEvent) {
        if(Minecraft.getMinecraft().thePlayer == null)
            return

        testScene.update()
        testScene.render()
    }
}
