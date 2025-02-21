package com.github.freedownloadhere.blocknodes2.node

import com.github.freedownloadhere.blocknodes2.gui.EzGui
import com.github.freedownloadhere.blocknodes2.gui.GuiManager
import com.github.freedownloadhere.blocknodes2.gui.GuiWindow
import com.github.freedownloadhere.blocknodes2.util.ChatHelper

object NodeSceneManager {
    var loadedScene : NodeScene? = null

    fun createNewScene() {
        data class SceneData(var name : String)
        val backing = SceneData("")

        EzGui.beginWindow("Create a new scene")
            EzGui.textBox(backing::name, "Enter a name for your scene")
            EzGui.button("Create") {
                saveCurrentScene()
                loadedScene = NodeScene(backing.name)
                ChatHelper.send("Created new scene: ${backing.name}")
            }
        EzGui.endWindow()
    }

    fun saveCurrentScene() {

    }

    fun loadGui() {
        if(loadedScene == null)
            loadSceneGui()
        else
            nodeListGui()
    }

    private fun loadSceneGui() {
        EzGui.beginWindow("Create or load a scene")
            EzGui.text("You can create a new scene:")
            EzGui.button("Create Scene") {
                createNewScene()
            }
            EzGui.text("Or you can load an existing scene:")
            EzGui.beginList()
                for(i in 1..10)
                    EzGui.button("Fake Scene $i") {
                        loadedScene = NodeScene()
                        // careful
                        nodeListGui()
                    }
            EzGui.endList()
        EzGui.endWindow()
    }

    private fun nodeListGui() {
        EzGui.beginWindow("Scene \"${loadedScene!!.name}\"")
            EzGui.text("blahblahblahblah blah blah blah")
            EzGui.button("Unload Scene") {
                loadedScene = null
                // careful
                loadSceneGui()
            }
            EzGui.button("Add new node") { }
            EzGui.text("Currently loaded:")
            EzGui.beginList()
                for(i in 1..15)
                    EzGui.button("Fake Node $i") { }
            EzGui.endList()
        EzGui.endWindow()
    }
}