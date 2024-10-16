package com.cyd.feature.random_meal

import androidx.compose.runtime.Composable
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState

@Composable
fun RandomMealScreen(
    uiState: RandomMealUiState,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToMealDetails: (Pair<String, String>) -> Unit = {},
) {
    when (uiState) {
        is RandomMealUiState.HasRandomMeal -> {
            RandomMealView(
                uiState.randomMeal,
                onLoadNextRandomMeal,
                onClickGoToMealDetails,
            )
        }
        is RandomMealUiState.NoRandomMeal -> {
            NoRandomMealView(uiState)
            }
        }
}
