package com.example.composetraining.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.feature.meals_by_category.MealListScreen
import com.example.composetraining.feature.meals_by_category.viewmodel.MealListViewModel

@Composable
fun MealListRoute(
    id: String,
    name: String,
    navController: NavHostController,
    viewModel: MealListViewModel
) {
    val state by remember { viewModel.uiState }
    MealListScreen(
        id,
        name,
        state.toUiState(),
        onMealClick = {
            navController.navigate(
                NavScreen.MealDetails.withStringArgs(
                    it.id,
                    it.name
                )
            )
        },
        initLoading = { viewModel.loadMealsByCategory(name) }
    )
}