package com.example.composetraining.core.navigation.route

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composetraining.core.ui.meal.screen.CategoryDetailsScreen

@Composable
fun CategoryDetailsRoute(
    id: String,
    name: String,
    navController: NavHostController,
    //viewModel: CategoriesViewModel
) {
    CategoryDetailsScreen(id, name)
//    val state by remember { viewModel.uiState }
//    CategoryListScreen(state.toUiState(),
//        {viewModel.onCategoryClick(it)}) /// OR GO TO NEW SCREEN CATEGORY DETAILS
}