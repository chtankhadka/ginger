package org.chtan.ginger.ginger.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ginger.composeapp.generated.resources.Res
import ginger.composeapp.generated.resources.bathMat
import ginger.composeapp.generated.resources.bathSheet
import ginger.composeapp.generated.resources.bathrobe
import ginger.composeapp.generated.resources.doubleBedSheet
import ginger.composeapp.generated.resources.doubleDuvetCover
import ginger.composeapp.generated.resources.handTowel
import ginger.composeapp.generated.resources.kingBedSheet
import ginger.composeapp.generated.resources.kingDuvetCover
import ginger.composeapp.generated.resources.pillowCase
import ginger.composeapp.generated.resources.pillowCases
import ginger.composeapp.generated.resources.singleBedSheet
import ginger.composeapp.generated.resources.singleDuvetCover
import ginger.composeapp.generated.resources.superKingBedSheet
import ginger.composeapp.generated.resources.superKingDuvetCover
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.chtan.ginger.ginger.presentation.MyIcon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

sealed class RoomItem(
    val id: String,
    val name: String,
    val icon: DrawableResource,
    val quantity: Int
) {
    data object SuperKingBedSheet : RoomItem(
        id = "SKS",
        name = "Super King Bed Sheet",
        icon = Res.drawable.superKingBedSheet,
        quantity = 1
    )

    data object KingBedSheet :
        RoomItem(id = "KS", name = "King Bed Sheet", icon = Res.drawable.kingBedSheet, quantity = 1)

    data object DoubleBedSheet : RoomItem(
        id = "DS",
        name = "Double Bed Sheet",
        icon = Res.drawable.doubleBedSheet,
        quantity = 1
    )

    data object SingleBedSheet : RoomItem(
        id = "SS",
        name = "Single Bed Sheet",
        icon = Res.drawable.singleBedSheet,
        quantity = 1
    )

    data object PillowCase :
        RoomItem(id = "PC", name = "Pillow Case", icon = Res.drawable.pillowCase, quantity = 1)

    data object PillowCases :
        RoomItem(id = "PC", name = "Pillow Cases", icon = Res.drawable.pillowCases, quantity = 4)

    data object SuperKingDuvet : RoomItem(
        id = "SKD",
        name = "Super King Duvet",
        icon = Res.drawable.superKingDuvetCover,
        quantity = 1
    )

    data object KingDuvet :
        RoomItem(id = "KD", name = "King Duvet", icon = Res.drawable.kingDuvetCover, quantity = 1)

    data object DoubleDuvet : RoomItem(
        id = "DD",
        name = "Double Duvet",
        icon = Res.drawable.doubleDuvetCover,
        quantity = 1
    )

    data object SingleDuvet : RoomItem(
        id = "SD",
        name = "Single Duvet",
        icon = Res.drawable.singleDuvetCover,
        quantity = 1
    )

    data object BathSheet :
        RoomItem(id = "BS", name = "Bath Sheet", icon = Res.drawable.bathSheet, quantity = 1)

    data object BathMat :
        RoomItem(id = "BM", name = "Bath Mat", icon = Res.drawable.bathMat, quantity = 1)

    data object Robe : RoomItem(id = "R", name = "Robe", icon = Res.drawable.bathrobe, quantity = 1)
    data object HandTowel :
        RoomItem(id = "HT", name = "Hand Towel", icon = Res.drawable.handTowel, quantity = 1)
}


@Composable
fun LinenItem(
    onClickItem: (RoomItem) -> Unit,
    onClickAddRemoveIcon: (RoomItem) -> Unit,
    addRemoveIcon: ImageVector,
    numbers: String?,
    item: RoomItem,
) {
    Box {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Card(
                modifier = Modifier.size(70.dp).padding(10.dp)
                    .clickable {
//                    nav.navigate("room/${room.id}")
                    },
                elevation = CardDefaults.cardElevation(10.dp),
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(item.icon),
                    contentDescription = ""
                )

            }

            numbers?.let {
                Card(
                    modifier = Modifier.padding(10.dp).size(17.dp).clip(CircleShape),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = numbers,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }

            if (numbers == null) {
                Card(
                    modifier = Modifier.padding(10.dp).size(17.dp).clip(CircleShape)
                        .align(Alignment.Center),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = item.quantity.toString(),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }

            IconButton(
                modifier = Modifier.align(alignment = Alignment.TopEnd)
                    .padding(start = 5.dp)
                    .size(15.dp),
                onClick = {
                    onClickAddRemoveIcon(item)
                },
                content = {
                    Icon(
                        imageVector = addRemoveIcon,
                        contentDescription = "room notification",
                    )
                }
            )


        }
    }

}

@Composable
fun CheckInCheckOutItem(
    icon: DrawableResource, description: String?, time: String
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(icon),
            contentDescription = ""
        )
        Text(time)
    }

}

@Composable
fun roomItemsDialog(
    listOfItems: List<RoomItem>,
    onClickAddRemoveItem: (RoomItem) -> Unit,
    onClickDismiss: (Boolean) -> Unit
) {

    var showAddingAnnimation by remember { mutableStateOf(0) }
    var clickedItem by remember { mutableStateOf(RoomItem.SuperKingBedSheet.icon) }
//    var endOffset by remember { mutableStateOf(Offset.Zero) }
//    var startOffset by remember { mutableStateOf(Offset.Zero) }


    Dialog(
        onDismissRequest = {},
        content = {
            Box(modifier = Modifier) {


                Column(
                    modifier = Modifier.fillMaxWidth().align(Alignment.Center)
                ) {

                    if(showAddingAnnimation != 0){
                        MovingAndFadingAnimation(
                            trigger = showAddingAnnimation,
                            image = clickedItem)
                    }


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
                            Text("add lines items")
                            MyIcon(
                                icon = Icons.Default.Close,
                                onClick = {
                                    onClickDismiss(true)
                                })
                        }

                        LazyVerticalGrid(

                            columns = GridCells.Adaptive(70.dp)
                        ) {

                            items(listOfItems) { item ->
                                LinenItem(
                                    onClickItem = {

                                    },
                                    onClickAddRemoveIcon = {
                                        onClickAddRemoveItem(item)
                                        showAddingAnnimation += 1
                                        clickedItem = item.icon

                                    },
                                    addRemoveIcon = Icons.Default.Add,
                                    numbers = null,
                                    item = item,

                                    )
                            }
                        }


                    }
                }


            }

        }
    )
}


@Composable
fun MovingAndFadingAnimation(
    trigger: Int,
    image: DrawableResource
) {
    // Animation state, reset on trigger change
    val offsetX = remember(trigger) { Animatable(0f) }
    val alpha = remember(trigger) { Animatable(1f) }

    // Launch animation when trigger changes
    LaunchedEffect(trigger) {

        // Reset to initial state
        offsetX.snapTo(0f)
        alpha.snapTo(1f)

        supervisorScope {
            launch {
                // Move to parent width minus card width (70.dp)
                offsetX.animateTo(
                    targetValue = 200f, // Adjust for card width
                    animationSpec = tween(durationMillis = 1000)
                )
            }
            launch {
                // Fade out
                alpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 1000) // Same duration as movement
                )
            }
        }
        // Optional: Delay before disappearing completely

    }

    // Animated Box

        Image(
            modifier = Modifier.size(70.dp).padding(10.dp)
                .offset(x = offsetX.value.dp)
                .alpha(alpha.value),
            painter = painterResource(image),
            contentDescription = ""
        )

    
}

@Composable
fun MovingAndFadingAnimation(
    startOffset: Offset,
    endOffset: Offset,
    trigger: Int
) {
    // Animation state, reset on trigger change
    val offset = remember(trigger) {
        Animatable(
            initialValue = DpOffset(startOffset.x.dp, startOffset.y.dp),
            typeConverter = DpOffset.VectorConverter
        )
    }
    val alpha = remember(trigger) { Animatable(1f) }

    // Launch animation when trigger changes
    LaunchedEffect(trigger) {
        // Reset to initial state
        offset.snapTo(DpOffset(startOffset.x.dp, startOffset.y.dp))
        alpha.snapTo(1f)

        // Move to end offsets (X and Y simultaneously)
        offset.animateTo(
            targetValue = DpOffset(endOffset.x.dp, endOffset.y.dp),
            animationSpec = tween(durationMillis = 2000)
        )
        // Fade out
        alpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000)
        )
        // Delay before disappearing completelyasında

        delay(500)
    }

    // Animated Box
    Box(
        modifier = Modifier
            .size(50.dp)
            .offset(offset.value.x, offset.value.y)
            .alpha(alpha.value)
            .background(Color.Blue)
    )
}