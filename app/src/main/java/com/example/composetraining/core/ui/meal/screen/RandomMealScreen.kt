package com.example.composetraining.core.ui.meal.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.core.ui.base.MealScaffold
import com.example.composetraining.core.ui.meal.views.NoRandomMealView
import com.example.composetraining.core.ui.meal.views.RandomMealView
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealUiState
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealScreen(
    navController: NavHostController,
    viewModel: RandomMealViewModel
) {
    val state by remember { viewModel.uiState }
    MealScaffold("Dish Of The Day") {
        when (val uiState = state.toUiState()) {
            is RandomMealUiState.HasRandomMeal -> {
                RandomMealView(uiState.randomMeal,
                    { viewModel.loadRandomMeal() },
                    { navController.navigate(NavScreen.CategoryList.route) })
            }
            is RandomMealUiState.NoRandomMeal -> {
                NoRandomMealView(uiState)
            }
        }
    }
}
