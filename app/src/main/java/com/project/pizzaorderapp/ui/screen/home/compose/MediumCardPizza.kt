package com.project.pizzaorderapp.ui.screen.home.compose

import androidx.compose.runtime.Composable
import com.project.pizzaorderapp.ui.screen.home.view_model.HomeUiState
import com.project.pizzaorderapp.ui.screen.home.view_model.TypeSize

@Composable
fun MediumCardPizza(
    state: HomeUiState,
    onSizeSelected: (TypeSize) -> Unit,
) {
    CardPizzaSize(
        text = "M",
        state = state,
        typeSize = TypeSize.Medium,
        onSizeSelected = { onSizeSelected(it) }
    )
}
