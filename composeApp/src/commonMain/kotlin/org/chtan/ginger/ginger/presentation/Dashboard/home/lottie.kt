package org.chtan.ginger.ginger.presentation.Dashboard.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ginger.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter

@Composable
fun LottieCard(
    modifier: Modifier = Modifier,
    filePath: String) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            archive = Res.readBytes(path = filePath),
            animationId = null
        )
    }

    Image(
        modifier = modifier,
        painter = rememberLottiePainter(
            composition = composition,
            iterations = Compottie.IterateForever
        ),
        contentDescription = "Lottie animation"
    )
}