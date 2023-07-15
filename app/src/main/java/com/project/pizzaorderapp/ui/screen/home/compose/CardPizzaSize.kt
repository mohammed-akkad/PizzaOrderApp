package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pizzaorderapp.ui.screen.home.view_model.HomeUiState
import com.project.pizzaorderapp.ui.screen.home.view_model.TypeSize

@Composable
fun CardPizzaSize(
    state: HomeUiState,
    text: String,
    modifier: Modifier = Modifier,
    typeSize: TypeSize,
    onSizeSelected: (TypeSize) -> Unit
) {
    val elevation by animateDpAsState(if (state.selectedSizePizza == typeSize) 16.dp else 0.dp)
    AnimatedVisibility(
        visible = state.selectedSizePizza == typeSize,
        enter = slideInHorizontally(initialOffsetX = { -it }),
        exit = slideOutHorizontally(targetOffsetX = { -it })
    ) {

    }
    Card(
        modifier = modifier
            .size(45.dp)
            .background(Color.White, CircleShape)
            .shadow(elevation = elevation, CircleShape),
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.White),
//        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onSizeSelected(typeSize) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}