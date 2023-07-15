package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.project.pizzaorderapp.ui.screen.home.view_model.IngredientUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Ingredients(items: IngredientUiState, size: Dp) {
    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items.ingredients.forEach {
            Image(
                modifier = Modifier
                    .width(size)
                    .clip(CircleShape),
                painter = painterResource(id = it),
                contentDescription = null,
                contentScale = ContentScale.Inside
            )
        }
    }
}