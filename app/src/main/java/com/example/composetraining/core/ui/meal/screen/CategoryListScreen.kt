package com.example.composetraining.core.ui.meal.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composetraining.core.ui.base.MealScaffold
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesViewModel

@Composable
fun CategoryListScreen(
    navController: NavHostController,
    viewModel: CategoriesViewModel
) {
    MealScaffold("Categories") {
        Box(Modifier.fillMaxSize()) {
            Text(text = "CategoryList")
        }
    }
}