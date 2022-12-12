package com.example.chooseyourdish.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.chooseyourdish.core.navigation.NavScreen
import com.example.chooseyourdish.feature.meal_categories.CategoryListScreen
import com.example.chooseyourdish.feature.meal_categories.viewmodel.CategoriesViewModel

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