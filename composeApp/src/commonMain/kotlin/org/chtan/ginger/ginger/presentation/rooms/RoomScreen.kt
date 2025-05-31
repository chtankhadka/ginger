package org.chtan.ginger.ginger.presentation.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ginger.composeapp.generated.resources.Res
import ginger.composeapp.generated.resources.checkIn
import ginger.composeapp.generated.resources.checkOut
import ginger.composeapp.generated.resources.room
import org.chtan.ginger.ginger.presentation.MyIcon
import org.chtan.ginger.ginger.presentation.components.CheckInCheckOutItem
import org.chtan.ginger.ginger.presentation.components.LinenItem
import org.chtan.ginger.ginger.presentation.components.RoomItem
import org.chtan.ginger.ginger.presentation.components.roomItemsDialog
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(navController: NavHostController) {
    val listOfLinens = listOf(
        RoomItem.SuperKingDuvet,
        RoomItem.KingDuvet,
        RoomItem.DoubleDuvet,
        RoomItem.SingleDuvet,
        RoomItem.SuperKingBedSheet,
        RoomItem.KingBedSheet,
        RoomItem.DoubleBedSheet,
        RoomItem.SingleBedSheet,
        RoomItem.PillowCase,
        RoomItem.PillowCases,
    )

    val listOfBathItems = listOf(
        RoomItem.BathSheet,
        RoomItem.BathMat,
        RoomItem.Robe,
        RoomItem.HandTowel
    )

    var listOfLinenUsed = remember {
        mutableStateListOf<RoomItem>()
    }
    var listOfBathItemUsed = remember {
        mutableStateListOf<RoomItem>()
    }

    var showLinenItems by remember { mutableStateOf(false) }
    var showBathItems by remember { mutableStateOf(false) }
    if (showBathItems) {
        roomItemsDialog(
            listOfItems = listOfBathItems,
            onClickAddRemoveItem = { item ->
                listOfBathItemUsed.add(item)
            },
            onClickDismiss = {
                showBathItems = !it
            }
        )
    }
    if (showLinenItems) {
        roomItemsDialog(
            listOfItems = listOfLinens,
            onClickAddRemoveItem = { item ->
                listOfLinenUsed.add(item)
            },
            onClickDismiss = {
                showLinenItems = !it
            })
    }
    Scaffold(
        modifier = Modifier, topBar = {
            TopAppBar(
                colors =
                    TopAppBarDefaults.topAppBarColors(Color.Transparent),
                title = {
                    Text(
                        text = "Room 1",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }, navigationIcon = {
                    Card(
                        modifier = Modifier.size(34.dp).clickable {
                            navController.popBackStack()
                        },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(2.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp).align(Alignment.Center),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = ""
                            )
                        }
                    }
                }, actions = {
                    Card(
                        modifier = Modifier.size(34.dp).clickable {
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
                                imageVector = Icons.Default.Settings,
                                tint = Color.White,
                                contentDescription = ""
                            )
                        }
                    }
                })
        }) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(150.dp),
                            painter = painterResource(Res.drawable.room),
                            contentDescription = ""
                        )
                        Column(modifier = Modifier.fillMaxWidth(1f)) {
                            CheckInCheckOutItem(
                                icon = Res.drawable.checkIn,
                                description = null,
                                time = "09:00 AM"
                            )

                            CheckInCheckOutItem(
                                icon = Res.drawable.checkOut, description = null, time = "09:00 AM"
                            )
                        }
                    }
                }

                Box(

                ) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(
                                color = MaterialTheme.colorScheme.primaryContainer
                            ).padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Linens Used")
                            MyIcon(
                                icon = Icons.Default.Add, onClick = {
                                    showLinenItems = true
                                })
                        }
                        LazyVerticalGrid(

                            columns = GridCells.Adaptive(70.dp)
                        ) {

                            items(listOfLinenUsed.distinct()) { item ->
                                LinenItem(
                                    item = item,
                                    onClickItem = {},
                                    onClickAddRemoveIcon = {
                                        listOfLinenUsed.remove(it)
                                    },
                                    addRemoveIcon = Icons.Default.Remove,
                                    numbers = listOfLinenUsed.filter { it == item }
                                        .sumOf { it.quantity }.toString(),

                                    )
                            }

                        }


                    }

                }

                Box(

                ) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(
                                color = MaterialTheme.colorScheme.primaryContainer
                            ).padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Bath Items Used")
                            MyIcon(
                                icon = Icons.Default.Add, onClick = {
                                    showBathItems = true
                                })
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(70.dp)
                        ) {

                            items(listOfBathItemUsed.distinctBy { it.id }) { item ->
                                LinenItem(
                                    item = item,
                                    onClickItem = {},
                                    onClickAddRemoveIcon = {
                                        listOfBathItemUsed.remove(it)
                                    },
                                    addRemoveIcon = Icons.Default.Remove,
                                    numbers = listOfBathItemUsed.filter { it.id == item.id }
                                        .sumOf { it.quantity }.toString(),

                                    )
                            }

                        }


                    }

                }
            }
        }


    }
}





