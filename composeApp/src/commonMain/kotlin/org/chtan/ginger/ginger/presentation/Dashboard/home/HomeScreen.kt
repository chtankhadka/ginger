package org.chtan.ginger.ginger.presentation.Dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class GingerRooms(
    val id: Int, val roomNo: Int, val roomDetails: String?, val roomSituation: RoomSituation
)

enum class RoomSituation(val situation: String, val color: Color, val time: String, val lottiePath: String) {
    CLEANED("Cleaned", Color.Green, "09:00 AM", "files/cleaning.lottie"),
    VACANT(
        "Vacant",
        Color.LightGray,
        "02:00 PM", "files/"
    ),
    OCCUPIED(
        "Occupied",
        Color.Red,
        "05:00 PM", "files/sleeping.lottie"
    ),
}


@Composable
fun HomeScreen(nav: NavHostController) {
// Rooms
    val listOfRooms = listOf<GingerRooms>(
        GingerRooms(1, 1, "Room 1", RoomSituation.CLEANED),
        GingerRooms(2, 2, "Room 2", RoomSituation.VACANT),
        GingerRooms(3, 3, "Room 3", RoomSituation.VACANT),
        GingerRooms(4, 4, "Room 4", RoomSituation.OCCUPIED),
        GingerRooms(5, 5, "Room 5", RoomSituation.CLEANED),
        GingerRooms(6, 6, "Room 6", RoomSituation.VACANT),
        GingerRooms(7, 7, "Room 7", RoomSituation.OCCUPIED),
        GingerRooms(8, 8, "Room 8", RoomSituation.OCCUPIED),
        GingerRooms(9, 9, "Room 9", RoomSituation.CLEANED),
        GingerRooms(10, 10, "Room 10", RoomSituation.VACANT)
    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(110.dp), modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(listOfRooms.size) {
            RoomCard(room = listOfRooms[it])
        }

    }
}

@Composable
fun RoomCard(room: GingerRooms) {
    Box {
        Box(modifier = Modifier.size(110.dp).align(Alignment.Center)) {
            Card(
                modifier = Modifier.size(100.dp).padding(10.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(room.roomSituation.color)
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = room.roomNo.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = room.roomSituation.situation,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,)

                Text(
                    text = room.roomSituation.time,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )

            }
            Icon(
                modifier = Modifier.align(alignment = Alignment.TopEnd).padding(end = 20.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = "room notification",
                tint = MaterialTheme.colorScheme.error
            )
            LottieCard(
                filePath = room.roomSituation.lottiePath,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(100.dp).padding(start = 20.dp, top = 20.dp)
            )
        }
    }

}