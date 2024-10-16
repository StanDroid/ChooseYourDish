package com.cyd.feature.random_meal

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun NoRandomMealView(
    state: RandomMealUiState,
) {
    Box {
        if (state.isLoading) {
            ProgressLoadingView()
        } else {
            Text(
                text = stringResource(R.string.no_random_meal),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true, name = "No data")
@Composable
fun NoRandomMealPreview() {
    NoRandomMealView(state = RandomMealUiState.NoRandomMeal(isLoading = false))
}

@Preview(showBackground = true, name = "loading")
@Composable
fun NoRandomMealLoadingPreview() {
    NoRandomMealView(state = RandomMealUiState.NoRandomMeal(isLoading = true))
}
