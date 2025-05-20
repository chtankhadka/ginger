package org.chtan.ginger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform