package org.chtan.ginger.ginger.presentation.rota

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ginger.composeapp.generated.resources.Res
import ginger.composeapp.generated.resources.room
import org.chtan.ginger.ginger.presentation.components.DropdownMenuWithDetails
import org.chtan.ginger.ginger.presentation.components.MyDropDownItems
import org.chtan.ginger.ginger.presentation.utils.convertMillisToDate
import org.jetbrains.compose.resources.painterResource



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RotaScreen(navController: NavHostController) {
    var showMenu by remember { mutableStateOf(true) }
    var selectedDate by remember { mutableStateOf("2025-05-12") }

    val employees = listOf(
        Employees("chetan", "9 AM - __", "2025-05-09"),
        Employees("Nikesh",  "10 AM - __", "2025-05-09"),
        Employees("Charu", "9 AM - __", "2025-05-09"),
        Employees("Harry",  "10 AM - __", "2025-05-09"),
        Employees("Angela", "9 AM - __", "2025-05-09"),
        Employees("Kyran",  "10 AM - __", "2025-05-09"),
        Employees("Harriet", "9 AM - __", "2025-05-09"),
        Employees("Sumitra",  "10 AM - __", "2025-05-09"),
        Employees("chetan", "9 AM - __", "2025-05-10"),
        Employees("Nikesh",  "10 AM - __", "2025-05-10"),
        Employees("Charu", "9 AM - __", "2025-05-10"),
        Employees("Harry",  "10 AM - __", "2025-05-10"),
        Employees("Angela", "9 AM - __", "2025-05-10"),
        Employees("Kyran",  "10 AM - __", "2025-05-10"),
        Employees("Harriet", "9 AM - __", "2025-05-10"),
        Employees("Sumitra",  "10 AM - __", "2025-05-10"),
        Employees("Nikesh",  "10 AM - __", "2025-05-11"),
        Employees("Charu", "9 AM - __", "2025-05-11"),
        Employees("Harry",  "10 AM - __", "2025-05-11"),
        Employees("chetan", "9 AM - __", "2025-05-12"),
        Employees("Nikesh",  "10 AM - __", "2025-05-12"),
        Employees("Charu", "9 AM - __", "2025-05-12"),
        Employees("Harry",  "10 AM - __", "2025-05-12"),
        Employees("Angela", "9 AM - __", "2025-05-12"),
        Employees("Kyran",  "10 AM - __", "2025-05-12"),
        Employees("Harriet", "9 AM - __", "2025-05-12"),
        Employees("Sumitra",  "10 AM - __", "2025-05-12"),
        Employees("chetan", "9 AM - __", "2025-05-13"),
        Employees("Nikesh",  "10 AM - __", "2025-05-13"),
        Employees("Charu", "9 AM - __", "2025-05-13"),
        Employees("Harry",  "10 AM - __", "2025-05-13"),
        Employees("Angela", "9 AM - __", "2025-05-13"),
        Employees("Kyran",  "10 AM - __", "2025-05-13"),
        Employees("Harriet", "9 AM - __", "2025-05-13"))

    val thisWeekRota = employees.distinctBy { it.day }.map { it.day }

    var showDateRange by remember { mutableStateOf(false) }




    if (showDateRange){
        DateRangePickerModal({

        },{
            showDateRange = false
        })
    }




    Scaffold(
        modifier = Modifier, topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.Transparent), title = {
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
                DropdownMenuWithDetails(
                    buttonIcon = Icons.Default.MoreVert, dropDownItems = listOf(
                        Pair<MyDropDownItems, List<MyDropDownItems>?>(
                            MyDropDownItems.AddRota, listOf(
                                MyDropDownItems.SelectDate, MyDropDownItems.SelectDateRange
                            )
                        ), Pair(MyDropDownItems.Note, null)
                    ), onClick = { myDropDownItem ->
                        when (myDropDownItem) {
                            MyDropDownItems.AddRota -> {

                            }

                            MyDropDownItems.Note -> {

                            }

                            MyDropDownItems.SelectDate -> {

                            }

                            MyDropDownItems.SelectDateRange -> {
                                showDateRange = true
                            }
                        }
                    })
            })
        }) {
        Box() {
            Column(
                modifier = Modifier.fillMaxSize().padding(it)
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.primary)
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "0202 to 2020"
                        )

                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = rememberLazyListState(initialFirstVisibleItemIndex = thisWeekRota.indexOf("2025-05-12")),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(thisWeekRota) { day ->
                            ElevatedFilterChip(selected =  day == selectedDate,
                                label = {
                                Text(day)
                            }, onClick = {
                                selectedDate = day
                            })
                        }

                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(employees.filter { it.day == selectedDate }) { item ->
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Image(
                                            modifier = Modifier.size(55.dp),
                                            painter = painterResource(Res.drawable.room),
                                            contentDescription = "image"
                                        )
                                        Column(

                                        ) {
                                            Text(item.time)
                                            Text(item.name)

                                        }
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}

data class Employees(
    val name: String,
    val time: String,
    val day: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {


    // Initialize the DateRangePickerState with the selectable dates
    val dateRangePickerState = rememberDateRangePickerState()


    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(
                    text = "Select date range"
                )
            },
            headline ={
                Text(
                    text = getDateRangeList(
                        startMillis = dateRangePickerState.selectedStartDateMillis,
                        endMillis = dateRangePickerState.selectedEndDateMillis).toString()
                )
            },
            showModeToggle = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        )
    }
}

fun getDateRangeList(startMillis: Long?, endMillis: Long?): List<String> {
    if (startMillis == null || endMillis == null) return emptyList()

    val millisPerDay = 86_400_000L
    val start = startMillis - (startMillis % millisPerDay)
    val end = endMillis - (endMillis % millisPerDay)

    val dateList = mutableListOf<String>()
    var current = start
    while (current <= end) {
        dateList.add(convertMillisToDate(current))
        current += millisPerDay
    }

    return dateList
}