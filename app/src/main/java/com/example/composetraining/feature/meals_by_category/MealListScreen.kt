package com.example.composetraining.feature.meals_by_category

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
import com.example.composetraining.core.data.model.mealdb.MealItem
import com.example.composetraining.core.ui.base.MealScaffold
import com.example.composetraining.core.ui.meal.MealItemView
import com.example.composetraining.core.ui.meal.ProgressLoadingView
import com.example.composetraining.feature.meal_details.viewmodel.MealListUiState

@Composable
fun MealListScreen(
    id: String,
    name: String,
    uiState: MealListUiState,
    onMealClick: (MealItem) -> Unit,
    initLoading: () -> Unit
) {
    MealScaffold(name) {
        Text(text = "ID is $id")
        when (uiState) {
            is MealListUiState.NoData -> {
                when {
                    uiState.isLoading -> { ProgressLoadingView() }
                    uiState.errorMessages.isNotEmpty() -> {
                        Box(Modifier.fillMaxSize()) {
                            Text(text = "No data")
                        }
                    }
                    else -> { initLoading.invoke() }
                }
            }
            is MealListUiState.HasData -> {
                val list = uiState.list
                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(list) { meal ->
                        MealItemView(meal, onMealClick)
                    }
                }
            }
        }
    }
}