package org.chtan.ginger.ginger.presentation

import org.chtan.ginger.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "you fucker boy, ${platform.name}!"
    }
}