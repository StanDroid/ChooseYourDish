package com.example.composetraining.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.feature.meal_categories.CategoryListScreen
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesViewModel

@Composable
fun CategoryListRoute(
    navController: NavHostController,
    viewModel: CategoriesViewModel
) {
    val state by remember { viewModel.uiState }
    CategoryListScreen(
        state.toUiState()
    ) {
        navController.navigate(
            NavScreen.MealList.withStringArgs(
                it.id,
                it.name
            )
        )
    }
}