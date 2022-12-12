package com.example.chooseyourdish.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.chooseyourdish.feature.meal_details.MealDetailsScreen
import com.example.chooseyourdish.feature.meal_details.viewmodel.MealDetailsViewModel

@Composable
fun MealDetailsRoute(
    id: String,
    name: String,
    navController: NavHostController,
    viewModel: MealDetailsViewModel
) {
    val state by remember { viewModel.uiState }
    MealDetailsScreen(
        id,
        name,
        state.toUiState(),
        loadMealDetailsAction = { viewModel.loadMealDetails(id) }
    )
}