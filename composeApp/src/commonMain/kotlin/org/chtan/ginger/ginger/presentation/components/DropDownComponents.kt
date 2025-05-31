package org.chtan.ginger.ginger.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MyDropDownItems(
    val name: String,
    val leadingIcon: ImageVector,
    val trailingIcon: ImageVector? = null,
    val isDivider: Boolean = false
) {
    data object AddRota : MyDropDownItems(
        name = "Add Rota",
        leadingIcon = Icons.Outlined.Work,
        trailingIcon = Icons.Outlined.ExpandMore
    )

    data object SelectDate : MyDropDownItems(
        name = "Select Date",
        leadingIcon = Icons.Default.CalendarToday,
        trailingIcon = Icons.Default.Add
    )

    data object SelectDateRange : MyDropDownItems(
        name = "Select Date Range",
        leadingIcon = Icons.Default.DateRange,
        trailingIcon = Icons.Default.Add
    )

    data object Note : MyDropDownItems(
        name = "Note",
        leadingIcon = Icons.Default.Note,
        trailingIcon = Icons.Default.Add
    )

}

@Composable
fun DropdownMenuWithDetails(
    buttonIcon: ImageVector,
    dropDownItems: List<Pair<MyDropDownItems, List<MyDropDownItems>?>>,
    onClick: (MyDropDownItems) -> Unit = {

    }
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(16.dp)
    ) {

        Card(
            modifier = Modifier.size(34.dp).clickable {
                expanded = !expanded
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
                    imageVector = buttonIcon,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }

        DropdownMenu(
            modifier = Modifier.animateContentSize(),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            dropDownItems.forEach { menuItem ->
                var subItemExpanded by remember { mutableStateOf(false) }
                DropdownMenuItem(text = { Text(menuItem.first.name) }, leadingIcon = {
                    Icon(
                        menuItem.first.leadingIcon, contentDescription = null
                    )
                }, trailingIcon = {
                    menuItem.first.trailingIcon?.let {
                        Icon(
                            modifier = Modifier.rotate(if (subItemExpanded) 180f else 0f),
                            imageVector = it,
                            contentDescription = null
                        )
                    }
                }, onClick = {
                    if (menuItem.second == null) {
                        onClick(menuItem.first)
                        expanded = false
                    } else {
                        subItemExpanded = !subItemExpanded

                    }

                })
                if (subItemExpanded) {

                    Column(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        menuItem.second?.let {
                            it.forEach {
                                DropdownMenuItem(contentPadding = PaddingValues(10.dp), text = {
                                    Text(
                                        text = it.name, fontSize = 10.sp
                                    )
                                }, leadingIcon = {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = it.leadingIcon,
                                        contentDescription = null
                                    )
                                }, onClick = {
                                    onClick(it)
                                    expanded = false
                                    subItemExpanded = !subItemExpanded

                                })
                                HorizontalDivider()
                            }


                        }

                    }
                }
            }

        }
    }
}