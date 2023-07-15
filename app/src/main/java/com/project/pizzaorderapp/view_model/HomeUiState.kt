package com.project.pizzaorderapp.view_model

data class HomeUiState(
    val selectedSizePizza: TypeSize = TypeSize.Small,
    val isSelected: Boolean = false,
    val ingredientUiState: List<IngredientUiState> = emptyList(),
    val breads: List<Int> = emptyList(),
    val selectedBreadIndex: Int = 0,

)

data class IngredientUiState(
    val imageIngredients: Int = 0,
    val ingredients: List<Int> = emptyList(),
    val isSelected: Boolean = false
)

enum class TypeSize() {
    Small,
    Medium,
    Large
}

