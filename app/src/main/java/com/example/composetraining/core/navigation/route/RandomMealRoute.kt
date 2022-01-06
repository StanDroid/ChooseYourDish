package com.example.composetraining.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.core.ui.meal.screen.RandomMealScreen
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealRoute(
    navController: NavHostController,
    viewModel: RandomMealViewModel
) {
    val state by remember { viewModel.uiState }
    RandomMealScreen(state.toUiState(),
        { viewModel.loadRandomMeal() },
        { navController.navigate(NavScreen.CategoryList.route) })
}
