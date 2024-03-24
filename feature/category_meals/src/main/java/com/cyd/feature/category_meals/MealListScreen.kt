package com.cyd.feature.category_meals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cyd.base.mealdb.MealItem
import com.cyd.feature.category_meals.viewmodel.MealListUiState
import com.cyd.ui.view.meal.MealItemView
import com.cyd.ui.view.meal.ProgressLoadingView

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