package com.example.composetraining.core.ui.meal.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composetraining.core.data.model.mealdb.Category
import com.example.composetraining.core.ui.base.MealScaffold
import com.example.composetraining.core.ui.meal.views.CategoryItemView
import com.example.composetraining.core.ui.meal.views.ProgressLoadingView
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesUiState

@Composable
fun CategoryListScreen(
    uiState: CategoriesUiState,
    onCategoryClick: (Category) -> Unit
) {
    MealScaffold("Categories") {
        when (uiState) {
            is CategoriesUiState.NoCategories -> {
                if (uiState.isLoading) {
                    ProgressLoadingView()
                } else {
                    Box(Modifier.fillMaxSize()) {
                        Text(text = "No categories")
                    }
                }
            }
            is CategoriesUiState.HasCategories -> {
                val list = uiState.list
                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(list) { category ->
                        CategoryItemView(category, onCategoryClick)
                    }
                }
            }
        }
    }
}