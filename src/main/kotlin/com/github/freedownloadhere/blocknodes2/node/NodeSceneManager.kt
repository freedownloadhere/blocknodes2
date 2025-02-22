package com.github.freedownloadhere.blocknodes2.node

object NodeSceneManager {
    var loadedScene : NodeScene? = null

    fun createNewScene() {

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

    }

    private fun nodeListGui() {

    }
}