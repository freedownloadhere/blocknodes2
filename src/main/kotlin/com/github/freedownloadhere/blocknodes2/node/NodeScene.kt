package com.github.freedownloadhere.blocknodes2.node

class NodeScene {
    private val contactTimeForAction = 1
    val nodeList = mutableListOf<Node>()

    fun update() {
        for(node in nodeList) {
            node.update()
            if(node.contactTime == contactTimeForAction)
                node.actionList.forEach{ it.execute() }
        }
    }

    fun render() {
        for(node in nodeList)
            node.render()
    }
}