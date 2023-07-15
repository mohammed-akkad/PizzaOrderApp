package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.pizzaorderapp.ui.theme.PaleGreen
import com.project.pizzaorderapp.ui.screen.home.view_model.IngredientUiState

@Composable
fun ItemPizzaIcon(onClickIngredient: (IngredientUiState) -> Unit, state: IngredientUiState) {
    val backgroundColor by animateColorAsState(if (state.isSelected) PaleGreen else Color.White)
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable { onClickIngredient(state) }
    ) {
        Image(
            painter = painterResource(id = state.itemPizzaIcon),
            contentDescription = null,
            modifier = Modifier.width(35.dp)
        )
    }
}
