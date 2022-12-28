package com.cyd.feature.meals_by_category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cyd.R
import com.cyd.core.data.model.mealdb.MealItem
import com.cyd.core.ui.meal.MealItemView
import com.cyd.core.ui.meal.ProgressLoadingView
import com.cyd.feature.meals_by_category.viewmodel.MealListUiState

@Composable
fun MealListScreen(
    id: String,
    name: String,
    uiState: MealListUiState,
    onMealClick: (MealItem) -> Unit,
    initLoading: () -> Unit
) {
    when (uiState) {
        is MealListUiState.NoData -> {
            when {
                uiState.isLoading -> {
                    ProgressLoadingView()
                }
                uiState.errorMessages.isNotEmpty() -> {
                    Box(Modifier.fillMaxSize()) {
                        Text(text = stringResource(R.string.no_data))
                    }
                }
                else -> {
                    initLoading.invoke()
                }
            }
            }
            is MealListUiState.HasData -> {
                val list = uiState.list
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(list) { meal ->
                        MealItemView(meal, onMealClick)
                    }
                }
            }
        }
}