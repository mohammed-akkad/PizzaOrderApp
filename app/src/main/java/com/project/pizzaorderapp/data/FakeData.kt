package com.project.pizzaorderapp.data

import com.project.pizzaorderapp.R
import com.project.pizzaorderapp.ui.screen.home.view_model.IngredientUiState
import javax.inject.Inject

class FakeData @Inject constructor() {
    fun getIngredient(): List<IngredientUiState> {
        return listOf(
            IngredientUiState(R.drawable.basil_3, getBasil()),
            IngredientUiState(R.drawable.onion_3, getOnion()),
            IngredientUiState(R.drawable.broccoli_3, getBroccoli()),
            IngredientUiState(R.drawable.mushroom_3, getMushroom()),
            IngredientUiState(R.drawable.sausage_3, getSausage()),
        )
    }

    fun getBread(): List<Int> {
        return listOf(
            R.drawable.bread_1,
            R.drawable.bread_2,
            R.drawable.bread_3,
            R.drawable.bread_4,
            R.drawable.bread_5,
        )
    }

    private fun getBasil(): List<Int> {
        return listOf(
            R.drawable.basil_1,
            R.drawable.basil_2,
            R.drawable.basil_3,
            R.drawable.basil_4,
            R.drawable.basil_5,
            R.drawable.basil_6,
            R.drawable.basil_7,
            R.drawable.basil_8,
            R.drawable.basil_9,
            R.drawable.basil_10,
        )
    }

    private fun getOnion(): List<Int> {
        return listOf(
            R.drawable.onion_1,
            R.drawable.onion_2,
            R.drawable.onion_3,
            R.drawable.onion_4,
            R.drawable.onion_5,
            R.drawable.onion_6,
            R.drawable.onion_7,
            R.drawable.onion_8,
            R.drawable.onion_9,
            R.drawable.onion_10,
        )
    }

    private fun getBroccoli(): List<Int> {
        return listOf(
            R.drawable.broccoli_1,
            R.drawable.broccoli_2,
            R.drawable.broccoli_3,
            R.drawable.broccoli_4,
            R.drawable.broccoli_5,
            R.drawable.broccoli_6,
            R.drawable.broccoli_7,
            R.drawable.broccoli_8,
            R.drawable.broccoli_9,
            R.drawable.broccoli_10,
        )
    }

    private fun getMushroom(): List<Int> {
        return listOf(
            R.drawable.mushroom_1,
            R.drawable.mushroom_2,
            R.drawable.mushroom_3,
            R.drawable.mushroom_4,
            R.drawable.mushroom_5,
            R.drawable.mushroom_6,
            R.drawable.mushroom_7,
            R.drawable.mushroom_8,
            R.drawable.mushroom_9,
            R.drawable.mushroom_10,
        )
    }

    private fun getSausage(): List<Int> {
        return listOf(
            R.drawable.sausage_1,
            R.drawable.sausage_2,
            R.drawable.sausage_3,
            R.drawable.sausage_4,
            R.drawable.sausage_5,
            R.drawable.sausage_6,
            R.drawable.sausage_7,
            R.drawable.sausage_8,
            R.drawable.sausage_9,
            R.drawable.sausage_10,
        )
    }

}