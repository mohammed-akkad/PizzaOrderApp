package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.runtime.Composable
import com.project.pizzaorderapp.ui.screen.home.view_model.HomeUiState
import com.project.pizzaorderapp.ui.screen.home.view_model.TypeSize

@Composable
fun SmallCardPizza(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
) {
    CardPizzaSize(
        text = "S",
        state = state,
        typeSize = TypeSize.Small,
        onSizeSelected = { onSizeSelected(it) })
}