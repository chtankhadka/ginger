package org.chtan.ginger.ginger.presentation.Dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.chtan.ginger.ginger.presentation.Dashboard.home.HomeScreen
import org.chtan.ginger.ginger.presentation.Dashboard.workingdays.WorkingDaysScreen

data class UserInnerPage(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    nav: NavHostController,

) {
    val bottomNavController = rememberNavController()
    var isExpanded by remember { mutableStateOf(false) }

    val items: List<UserInnerPage> = remember {
        listOf(
            UserInnerPage(route = "home", label = "Home", icon = Icons.Default.House),
            UserInnerPage(route = "workingdays", label = "Working Days", icon = Icons.Default.Work)

        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                title = {
                    Text(
                        text = "Mr Ulala",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                navigationIcon = {
                    Card(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                // nav.navigate(Destination.Screen.NotificationScreen.route)
                            },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(2.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp).align(Alignment.Center),
                                imageVector = Icons.Default.People,
                                tint = Color.White,
                                contentDescription = ""
                            )
                        }
                    }
                },
                actions = {
                    Card(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                // nav.navigate(Destination.Screen.NotificationScreen.route)
                            },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(2.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp).align(alignment = Alignment.Center),
                                imageVector = Icons.Default.Notifications,
                                tint = Color.White,
                                contentDescription = ""
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(5.dp))
                    Card(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                nav.navigate("rotascreen")
                            },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(2.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp).align(Alignment.Center),
                                imageVector = Icons.AutoMirrored.Filled.Message,
                                tint = Color.White,
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                items.forEach { screen ->
                    val isSelected =
                        navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true
                    val color = if (isSelected) Color.White else MaterialTheme.colorScheme.outline

                    CompositionLocalProvider(LocalContentColor provides color) {
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                    LocalAbsoluteTonalElevation.current
                                )
                            ),
                            icon = {
                                Card(
                                    modifier = Modifier.size(34.dp),
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                                    elevation = CardDefaults.cardElevation(10.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(2.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .align(Alignment.BottomCenter)
                                                .size(20.dp),
                                            imageVector = screen.icon,
                                            tint = color,
                                            contentDescription = ""
                                        )

                                    }

                                }
                            },
                            selected = isSelected,
                            onClick = { bottomNavController.navigate(screen.route) },
                            label = {},
                            alwaysShowLabel = false
                        )
                    }


                }
            }



            //////
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = "home",
            ) {
                composable("home") {
                    HomeScreen(nav)
                }
                composable("workingdays") {
                    WorkingDaysScreen()
                }}



        }
    }
}