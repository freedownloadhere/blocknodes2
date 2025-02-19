package com.github.freedownloadhere.blocknodes2.node

import com.github.freedownloadhere.blocknodes2.gui.GuiManager
import com.github.freedownloadhere.blocknodes2.gui.GuiWindow

object NodeSceneManager {
    var loadedScene : NodeScene? = null

    fun requestGui() : GuiWindow {
        return if(loadedScene == null)
            loadSceneGui()
        else
            nodeListGui()
    }

    private fun loadSceneGui() : GuiWindow {
        val window = GuiWindow(300, 200, "Create or load a scene")
        with(window.contents) {
            newText("You can create a new scene:")
            newButton("Create") { }
            newText("Or you can load an existing scene:")
            newText("(sourced from .minecraft/blocknodes2)")
            with(newScrollableList(280, 300).contents) {
                for(i in 1..10)
                    newButton("Placeholder for scene $i") {
                        loadedScene = NodeScene()
                        GuiManager.base.addChild(nodeListGui())
                    }
            }
        }
        return window
    }

    private fun nodeListGui() : GuiWindow {
        val window = GuiWindow(300, 200, "Scene \"${loadedScene!!.name}\"")
        with(window.contents) {
            newButton("Add new node") { }
            newText("Currently loaded nodes:")
            with(newScrollableList(280, 300).contents) {
                for(i in 1..20)
                    newButton("Node $i") { }
            }
        }
        return window
    }
}