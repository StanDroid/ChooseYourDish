package com.example.composetraining.feature.random_meal

import androidx.compose.runtime.Composable
import com.example.composetraining.core.ui.base.MealScaffold
import com.example.composetraining.core.ui.meal.NoRandomMealView
import com.example.composetraining.core.ui.meal.RandomMealView
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealUiState

@Composable
fun RandomMealScreen(
    uiState: RandomMealUiState,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToCategories: () -> Unit = {}
) {
    MealScaffold("Dish Of The Day") {
        when (uiState) {
            is RandomMealUiState.HasRandomMeal -> {
                RandomMealView(
                    uiState.randomMeal,
                    onLoadNextRandomMeal,
                    onClickGoToCategories
                )
            }
            is RandomMealUiState.NoRandomMeal -> {
                NoRandomMealView(
                    uiState,
                    onClickGoToCategories
                )
            }
        }
    }
}
