package com.project.pizzaorderapp.view_model

import androidx.lifecycle.ViewModel
import com.project.pizzaorderapp.data.FakeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fakeData: FakeData
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val selectedIngredientsMap = mutableMapOf<Int, List<Int>>()


    init {
//        getIngredient()
        getBread()
    }

    private fun getBread() {
        val getBread = fakeData.getBread()
        val response = fakeData.getIngredient()
        _state.update {
            it.copy(
                breads = getBread,
                ingredientUiState = response
            )
        }
    }

    fun selectedBread(index: Int) {
        val selectedBread = fakeData.getBread()[index]
        _state.update { currentState ->
            val updatedIngredients = currentState.ingredientUiState.map { existingIngredient ->
                existingIngredient.copy(isSelected = false)
            }
            currentState.copy(
                breads = currentState.breads,
                ingredientUiState = updatedIngredients,
                selectedBreadIndex = index,
            )
        }
    }



//    private fun getIngredient() {
//        val response = fakeData.getIngredient()
//        _state.update {
//            it.copy(
//                ingredientUiState = response
//            )
//        }
//    }

    fun selectedSize(size: TypeSize) {
        when (size) {
            TypeSize.Small -> {
                _state.update { it.copy(selectedSizePizza = size,isSelected = true) }
            }

            TypeSize.Medium -> {
                _state.update { it.copy(selectedSizePizza = size,isSelected = true) }
            }

            TypeSize.Large -> {
                _state.update { it.copy(selectedSizePizza = size,isSelected = true) }
            }
        }
    }

    fun isSelectedIngredient(item: IngredientUiState) {
        _state.update { currentState ->
            val updatedIngredients = currentState.ingredientUiState.map { existingIngredient ->
                if (existingIngredient == item) {
                    existingIngredient.copy(
                        isSelected = !existingIngredient.isSelected,
                    )
                } else {
                    existingIngredient
                }
            }
            currentState.copy(ingredientUiState = updatedIngredients)
        }
    }

//    fun isSelectedIngredient(item: IngredientUiState) {
//        _state.update { currentState ->
//            val updatedIngredients = currentState.ingredientUiState.map { existingIngredient ->
//                if (existingIngredient == item) {
//                    val isSelected = !existingIngredient.isSelected
//                    existingIngredient.copy(isSelected = isSelected)
//                } else {
//                    existingIngredient
//                }
//            }
//            val selectedIngredients = updatedIngredients
//                .filterIndexed { index, ingredient -> ingredient.isSelected }
//                .map { it.imageIngredients }
//
//            selectedIngredientsMap[currentState.selectedBreadIndex] = selectedIngredients
//
//            currentState.copy(ingredientUiState = updatedIngredients)
//        }
//    }



}