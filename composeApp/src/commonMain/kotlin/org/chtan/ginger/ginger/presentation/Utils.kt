package org.chtan.ginger.ginger.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyIcon(
    onClick: () -> Unit,
    icon: ImageVector,
){
    Card(
        modifier = Modifier
            .size(24.dp)
            .clickable {
onClick()            },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(2.dp)
        ) {
            Icon(
                modifier = Modifier.size(20.dp).align(Alignment.Center),
                imageVector = icon,
                tint = Color.White,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun MyCircleNumber(
    onClick: () -> Unit,
    number: String,
    modifier: Modifier = Modifier,
){
    Box(modifier = modifier){

        Card(
            modifier = Modifier.clip(CircleShape),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(10.dp),
        ) {

            Text(
                modifier = Modifier.fillMaxSize(),
                text = number,
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }
    }

}