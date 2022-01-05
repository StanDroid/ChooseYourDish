package com.example.composetraining.core.ui.meal.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.core.ui.base.MealScaffold
import com.example.composetraining.core.ui.meal.NoRandomMealView
import com.example.composetraining.core.ui.meal.RandomMealView
import com.example.composetraining.feature.random_meal.viewmodel.HomeUiState
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealScreen(
    navController: NavHostController,
    viewModel: RandomMealViewModel
) {
    MealScaffold("Dish Of The Day") {
        val state by viewModel.uiState
        when (val uiState = state.toUiState()) {
            is HomeUiState.HasRandomMeal -> {
                RandomMealView(uiState.randomMeal,
                    { viewModel.loadRandomMeal() },
                    { navController.navigate(NavScreen.CategoryList.route) }
                )
            }
            is HomeUiState.NoRandomMeal -> {
                NoRandomMealView(uiState)
            }
        }
    }
}
