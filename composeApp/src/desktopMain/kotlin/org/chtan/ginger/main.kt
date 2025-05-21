package org.chtan.ginger

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chtan.ginger.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true,
        title = "ginger",
    ) {
        App()
    }
}