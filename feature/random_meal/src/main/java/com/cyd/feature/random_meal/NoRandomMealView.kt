package com.cyd.feature.random_meal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun NoRandomMealView(
    state: RandomMealUiState,
    onClickGoToCategories: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { onClickGoToCategories.invoke() },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(text = stringResource(id = R.string.go_to_categories))
        }
        Box(
            Modifier
                .weight(1f)
                .align(CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                ProgressLoadingView()
            } else {
                Text(
                    text = stringResource(R.string.no_random_meal),
                    style = MaterialTheme.typography.subtitle1
                )
            }
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
