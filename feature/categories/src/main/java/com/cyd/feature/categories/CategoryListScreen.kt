package com.cyd.feature.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.cyd.base.mealdb.Category
import com.cyd.feature.categories.viewmodel.CategoriesUiState
import com.cyd.ui.view.base.CategoryListScreenConstants.CATEGORY_LIST
import com.cyd.ui.view.meal.CategoryItemView
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun CategoryListScreen(
    uiState: CategoriesUiState,
    onCategoryClick: (Category) -> Unit
) {
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
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.testTag(CATEGORY_LIST)
                ) {
                    items(list) { category ->
                        CategoryItemView(category, onCategoryClick)
                    }
                }
            }
        }
}