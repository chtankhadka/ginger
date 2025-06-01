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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ginger.composeapp.generated.resources.Res
import ginger.composeapp.generated.resources.room
import kotlinx.coroutines.launch
import org.chtan.ginger.ginger.presentation.components.DropdownMenuWithDetails
import org.chtan.ginger.ginger.presentation.components.MyDropDownItems
import org.chtan.ginger.ginger.presentation.utils.convertMillisToDate
import org.chtan.ginger.ginger.presentation.utils.currentDate
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RotaScreen(navController: NavHostController) {
    var showMenu by remember { mutableStateOf(true) }
    var selectedDate by remember { mutableStateOf(currentDate()) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    val listOfRotas = remember {
        mutableStateListOf(
            Rota(
                startToEndDate = "2025-05-09 to 2025-05-13", workDates = listOf(
                    WorkDates(
                        date = "2025-05-09", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __"),
                            Employees("Sumitra", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-10", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __"),
                            Employees("Sumitra", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-11", employees = listOf(
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-12", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __"),
                            Employees("Sumitra", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-13", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __")
                        )
                    )
                )
            ), Rota(
                startToEndDate = "2025-05-15 to 2025-05-22", workDates = listOf(
                    WorkDates(
                        date = "2025-05-15", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __"),
                            Employees("Sumitra", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-16", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __"),
                            Employees("Sumitra", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-17", employees = listOf(
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-05-18", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Charu", "9 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __"),
                            Employees("Sumitra", "10 AM - __")
                        )
                    ), WorkDates(
                        date = "2025-06-01", employees = listOf(
                            Employees("chetan", "9 AM - __"),
                            Employees("Nikesh", "10 AM - __"),
                            Employees("Harry", "10 AM - __"),
                            Employees("Angela", "9 AM - __"),
                            Employees("Kyran", "10 AM - __"),
                            Employees("Harriet", "9 AM - __")
                        )
                    )
                )
            )
        )
    }
    //pager
    val pagerState =
        rememberPagerState(initialPage = listOfRotas.find { it.workDates.find { it.date == selectedDate } != null }
            ?.let { listOfRotas.indexOf(it) } ?: 0, pageCount = {
            listOfRotas.size
        })

    var showDateRange by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    var newDateRange by remember { mutableStateOf<Boolean>(false) }
    LaunchedEffect(newDateRange) {
        if (newDateRange) {
            pagerState.animateScrollToPage(listOfRotas.size - 1)
            newDateRange = false // Reset to avoid repeated triggers
        }
    }
    if (showDateRange) {
        DateRangePickerModal({
            if (it != null) {
                listOfRotas.add(
                    Rota(
                        startToEndDate = it.first, workDates = it.second.map { date ->
                            WorkDates(
                                date = date, employees = emptyList()
                            )

                        })
                )
                newDateRange = true
            }
            showDateRange = false

        }, {
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
        }) {scaffolPadding ->
        Box() {
            HorizontalPager(
                userScrollEnabled = false, state = pagerState, pageContent = { page ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(scaffolPadding)
                    ) {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.primary)
                                    .padding(5.dp), verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = listOfRotas[page].startToEndDate
                                )

                                if (page != 0) {
                                    IconButton(onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                            contentDescription = null
                                        )

                                    }
                                }

                                if (page != listOfRotas.size - 1) {
                                    IconButton(onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                            contentDescription = null
                                        )
                                    }
                                }


                            }

                            LazyRow(
                                modifier = Modifier.fillMaxWidth(), state = rememberLazyListState(
                                    initialFirstVisibleItemIndex = 1
                                ), horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                items(listOfRotas[page].workDates) { day ->
                                    ElevatedFilterChip(
                                        selected = day.date == selectedDate,
                                        label = {
                                            Text(day.date)
                                        },
                                        onClick = {
                                            selectedDate = day.date
                                        })
                                }

                            }

                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                items(
                                    listOfRotas[page].workDates.find { it.date == selectedDate }?.employees
                                        ?: emptyList()
                                ) { item ->
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
                                item {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        IconButton(
                                            colors = IconButtonDefaults.iconButtonColors(
                                            MaterialTheme.colorScheme.primary
                                        ), onClick = {
                                            showBottomSheet = true
                                        }, content = {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = null
                                            )
                                        })
                                    }

                                }
                            }


                        }
                    }

                }

            )
            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxSize().padding(scaffolPadding),
                    sheetState = bottomSheetState,
                    onDismissRequest = { showBottomSheet = false }, content = {
                    Text("hello")
                })
            }


        }
    }
}

data class Rota(
    val startToEndDate: String, val workDates: List<WorkDates>
)

data class WorkDates(
    val date: String, val employees: List<Employees>
)

data class Employees(
    val name: String, val time: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<String, List<String>>?) -> Unit, onDismiss: () -> Unit
) {
    // Initialize the DateRangePickerState with the selectable dates
    val dateRangePickerState = rememberDateRangePickerState()
    DatePickerDialog(onDismissRequest = onDismiss, confirmButton = {
        TextButton(
            onClick = {
                if (dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null) {
                    onDateRangeSelected(
                        Pair(
                            "${
                                convertMillisToDate(
                                    dateRangePickerState.selectedStartDateMillis!!
                                )
                            } - ${convertMillisToDate(dateRangePickerState.selectedEndDateMillis!!)}",
                            getDateRangeList(
                                dateRangePickerState.selectedStartDateMillis!!,
                                dateRangePickerState.selectedEndDateMillis!!
                            )
                        )
                    )
                } else {
                    onDateRangeSelected(null)
                }
                onDismiss()
            }) {
            Text("OK")
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancel")
        }
    }) {
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(
                    text = "Select date range"
                )
            },
            headline = {
                Text(
                    text = "${
                        if (dateRangePickerState.selectedStartDateMillis != null) {
                            convertMillisToDate(dateRangePickerState.selectedStartDateMillis!!)

                        } else {
                            "Start Date"
                        }
                    } - ${
                        if (dateRangePickerState.selectedEndDateMillis != null) {
                            convertMillisToDate(dateRangePickerState.selectedEndDateMillis!!)
                        } else {
                            "End Date"
                        }
                    }"


//                        getDateRangeList(
//                        startMillis = dateRangePickerState.selectedStartDateMillis,
//                        endMillis = dateRangePickerState.selectedEndDateMillis).toString()
                )
            },
            showModeToggle = true,
            modifier = Modifier.fillMaxWidth().height(500.dp).padding(16.dp)
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