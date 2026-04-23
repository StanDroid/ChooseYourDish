package com.cyd.feature.categorymeals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cyd.base.model.MealItem
import com.cyd.base.viewmodel.UiState
import com.cyd.ui.view.meal.MealItemView
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun MealListScreen(
    uiState: UiState<List<MealItem>>,
    onMealClick: (MealItem) -> Unit,
) {
    when (uiState) {
        is UiState.InProgress -> {
            ProgressLoadingView()
        }

        is UiState.NoData -> {
            Box(Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.no_data),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        is UiState.HasData -> {
            val list = uiState.data
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(list) { meal ->
                    MealItemView(meal, onMealClick)
                }
            }
        }
    }
}
