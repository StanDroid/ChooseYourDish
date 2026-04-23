package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.core.navigation.CydBackHandler
import com.cyd.core.navigation.Graph
import com.cyd.feature.categories.CategoryListScreen
import com.cyd.feature.categories.viewmodel.CategoriesViewModel

@Composable
fun CategoryListRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<CategoriesViewModel>()
    CydBackHandler(navController)
    CategoryListScreen(viewModel) {
        navController.navigate(
            Graph.CategoriesGraph.MealListScreen.withStringArgs(
                it.id,
                it.name,
            ),
        )
    }
}
