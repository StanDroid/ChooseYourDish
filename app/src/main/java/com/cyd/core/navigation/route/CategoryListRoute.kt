package com.cyd.core.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.cyd.core.navigation.NavScreen
import com.cyd.core.ui.base.MealScaffold
import com.cyd.feature.meal_categories.CategoryListScreen
import com.cyd.feature.meal_categories.viewmodel.CategoriesViewModel

@Composable
fun CategoryListRoute(
    navController: NavHostController,
    viewModel: CategoriesViewModel
) {
    val state by remember { viewModel.uiState }
    MealScaffold(
        "Categories",
        icon = Icons.Default.ArrowBack,
        onIconClick = { navController.navigateUp() }
    ) {
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
}