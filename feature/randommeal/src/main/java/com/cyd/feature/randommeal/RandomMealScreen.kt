package com.cyd.feature.randommeal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cyd.base.model.RandomMeal
import com.cyd.base.viewmodel.UiState
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun RandomMealScreen(
    modifier: Modifier = Modifier,
    uiState: UiState<RandomMeal>,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToMealDetails: (Pair<String, String>) -> Unit = {},
) {
    Box(modifier.fillMaxSize()) {
        when (uiState) {
            is UiState.HasData -> {
                RandomMealView(
                    uiState.data,
                    onLoadNextRandomMeal,
                    onClickGoToMealDetails,
                )
            }

            is UiState.NoData -> {
                NoRandomMealView()
            }

            is UiState.InProgress -> {
                ProgressLoadingView()
            }
        }
    }
}
