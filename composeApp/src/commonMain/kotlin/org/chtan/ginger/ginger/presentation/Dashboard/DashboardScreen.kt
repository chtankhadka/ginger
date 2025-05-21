package org.chtan.ginger.ginger.presentation.Dashboard

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import org.chtan.ginger.ginger.presentation.Dashboard.home.HomeScreen

data class UserInnerPage(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val offset: Offset,
    val count: String = "",
    val isBadge: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    nav: NavHostController,

) {
    val bottomNavController = rememberNavController()
    var isExpanded by remember { mutableStateOf(false) }
    val radius = 100f


    val homeOffset by animateOffsetAsState(
        targetValue = if (isExpanded) Offset(-radius * 1f, radius * 0f) else Offset.Zero,
        label = "HomeOffset"
    )
    val mapOffset by animateOffsetAsState(
        targetValue = if (isExpanded) Offset(-radius * 0.707f, -radius * 0.707f) else Offset.Zero,
        label = "MapOffset"
    )
    val allOffset by animateOffsetAsState(
        targetValue = if (isExpanded) Offset(radius * 0f, -radius * 1f) else Offset.Zero,
        label = "AllOffset"
    )

    val items: List<UserInnerPage> = listOf(
        UserInnerPage("matchedusers", "matchedusers", Icons.Default.Home, homeOffset),
        UserInnerPage("map", "Map", Icons.Default.Home, mapOffset),
        UserInnerPage("all", "All", Icons.Default.Home, allOffset),
    )

    Scaffold(
        floatingActionButton = {
            Box {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                items.forEach { routeItem ->
                    val isSelected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == routeItem.route
                    } == true
                    val color = if (isSelected) Color.White else MaterialTheme.colorScheme.outline

                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(routeItem.offset.x.dp, routeItem.offset.y.dp)
                            .clip(CircleShape)
                            .border(width = if (isSelected) 1.dp else 0.dp, color = if (isSelected) color else Color.Transparent, shape = CircleShape)

                            .clickable {
                                if (!isSelected) bottomNavController.navigate(routeItem.route)
                                isExpanded = !isExpanded
                            },
                        // Using a sample image URL since the original was invalid
                        model = "https://picsum.photos/200", // Valid sample image
                        contentDescription = routeItem.label,
                        contentScale = ContentScale.Crop
                    )
                }

                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .clickable {
                            isExpanded = !isExpanded
                        },
                    model = "https://picsum.photos/200", // Valid sample image
                    contentDescription = "Toggle button",
                    contentScale = ContentScale.Crop
                )
            }
        },
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
                }}



        }
    }
}