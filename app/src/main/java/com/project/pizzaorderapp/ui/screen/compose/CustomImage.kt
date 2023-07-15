package com.project.pizzaorderapp.ui.screen.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun CustomImage(
    painter: Painter,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null,
        contentScale = contentScale
    )
}