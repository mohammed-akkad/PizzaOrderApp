package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.runtime.Composable
import com.project.pizzaorderapp.ui.screen.home.view_model.HomeUiState
import com.project.pizzaorderapp.ui.screen.home.view_model.TypeSize

@Composable
fun LargeCardPizza(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
) {
    CardPizzaSize(text = "L",
        state = state,
        typeSize = TypeSize.Large,
        onSizeSelected = { onSizeSelected(it) }
    )
}