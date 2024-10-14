package com.cyd.feature.meal_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cyd.feature.meal_details.viewmodel.MealDetailsUiState
import com.cyd.ui.view.meal.MealDetailsView
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun MealDetailsScreen(
    id: String,
    name: String,
    uiState: MealDetailsUiState,
    loadMealDetailsAction: () -> Unit,
    makeAsFavoriteAction: () -> Unit,
) {
        when (uiState) {
            is MealDetailsUiState.NoData -> {
                when {
                    uiState.isLoading -> { ProgressLoadingView() }
                    uiState.errorMessages.isNotEmpty() -> {
                        Box(Modifier.fillMaxSize()) {
                            Text(text = "No data")
                        }
                    }
                    else -> { loadMealDetailsAction.invoke() }
                }
            }
            is MealDetailsUiState.HasData -> {
                val meal = uiState.data
                MealDetailsView(meal, makeAsFavoriteAction)
            }
        }
}