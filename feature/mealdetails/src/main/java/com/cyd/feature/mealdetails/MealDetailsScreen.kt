package com.cyd.feature.mealdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cyd.base.model.Meal
import com.cyd.base.viewmodel.UiState
import com.cyd.ui.view.meal.MealDetailsView
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun MealDetailsScreen(
    uiState: UiState<Meal>,
    tapOnFavoritesAction: () -> Unit,
) {
    when (uiState) {
        is UiState.InProgress -> ProgressLoadingView()
        is UiState.NoData ->
            Box(Modifier.fillMaxSize()) {
                Text(text = "No data")
            }

        is UiState.HasData -> {
            val meal = uiState.data
            MealDetailsView(meal, tapOnFavoritesAction)
        }
    }
}
