package com.cyd.feature.random_meal

import androidx.compose.runtime.Composable
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState

@Composable
fun RandomMealScreen(
    uiState: RandomMealUiState,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToCategories: () -> Unit = {},
    onClickGoToIngredients: () -> Unit = {},
    onClickGoToFavorites: () -> Unit = {},
    onClickGoToMealDetails: (Pair<String, String>) -> Unit = {},
) {
    when (uiState) {
        is RandomMealUiState.HasRandomMeal -> {
            RandomMealView(
                uiState.randomMeal,
                onLoadNextRandomMeal,
                onClickGoToCategories,
                onClickGoToIngredients,
                onClickGoToFavorites,
                onClickGoToMealDetails,
            )
        }
        is RandomMealUiState.NoRandomMeal -> {
            NoRandomMealView(uiState)
            }
        }
}
