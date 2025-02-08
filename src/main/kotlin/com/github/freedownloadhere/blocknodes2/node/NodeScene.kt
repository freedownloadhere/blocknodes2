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
        //var i = 1
        for(node in nodeList) {
            //println("===== rendering cube $i =====")
            node.render()
            //i++
        }
    }
}