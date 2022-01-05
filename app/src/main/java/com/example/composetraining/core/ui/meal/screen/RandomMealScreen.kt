package com.example.composetraining.core.ui.meal.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composetraining.core.ui.meal.NoRandomMealView
import com.example.composetraining.core.ui.meal.RandomMealView
import com.example.composetraining.feature.random_meal.viewmodel.HomeUiState
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealScreen(
    navController: NavHostController,
    viewModel: RandomMealViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
    ) {
        val state by viewModel.uiState
        when (val uiState = state.toUiState()) {
            is HomeUiState.HasRandomMeal -> {
                RandomMealView(uiState.randomMeal) { viewModel.loadRandomMeal() }
            }
            is HomeUiState.NoRandomMeal -> {
                NoRandomMealView(uiState)
            }
        }
    }
}
