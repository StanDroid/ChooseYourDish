package com.example.composetraining.core.ui.meal.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.composetraining.core.ui.base.MealScaffold

@Composable
fun CategoryDetailsScreen(
    id: String,
    name: String,
    //uiState: CategoriesUiState,
//    onCategoryClick: (String) -> Unit
) {
    MealScaffold(name) {
        Text(text = "ID is $id")
//        when (uiState) {
//            is CategoriesUiState.NoCategories -> {
//                if (uiState.isLoading) {
//                    Box(Modifier.fillMaxSize()) {
//                        CircularProgressIndicator()
//                    }
//                } else {
//                    Box(Modifier.fillMaxSize()) {
//                        Text(text = "No categories")
//                    }
//                }
//            }
//            is CategoriesUiState.HasCategories -> {
//                val list = uiState.list
//                LazyColumn() {
//                    items(list) { category ->
//                        CategoryItemView(category, onCategoryClick)
//                    }
//                }
//            }
//        }
    }
}