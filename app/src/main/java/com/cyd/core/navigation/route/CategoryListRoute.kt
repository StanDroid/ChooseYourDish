package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.core.navigation.CydBackHandler
import com.cyd.core.navigation.Graph
import com.cyd.feature.categories.CategoryListScreen
import com.cyd.feature.categories.viewmodel.CategoriesViewModel

@Composable
fun CategoryListRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<CategoriesViewModel>()
    val state by remember { viewModel.uiState }
    CydBackHandler(navController)
        CategoryListScreen(
            state.toUiState()
        ) {
            navController.navigate(
                Graph.CategoriesGraph.MealListScreen.withStringArgs(
                    it.id,
                    it.name
                )
            )
        }
}