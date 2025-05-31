package org.chtan.ginger.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.chtan.ginger.ginger.presentation.Dashboard.DashboardScreen
import org.chtan.ginger.ginger.presentation.rooms.RoomScreen
import org.chtan.ginger.ginger.presentation.rota.RotaScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val navController = rememberNavController()

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavHost(
                navController = navController,
                startDestination = "dashboard"){
                composable("dashboard"){
                    DashboardScreen(navController)
                }
                composable("room"){
                    RoomScreen(navController)
                }
                composable("rotascreen"){
                    RotaScreen(navController)
                }

            }

        }
    }
}