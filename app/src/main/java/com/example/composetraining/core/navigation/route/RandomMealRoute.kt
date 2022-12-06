package com.example.composetraining.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.feature.random_meal.RandomMealScreen
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModelState


@Composable
fun RandomMealRoute(
    navController: NavHostController,
    viewModel: RandomMealViewModel
) {
    val state: RandomMealViewModelState by viewModel.uiState.collectAsState()

    RandomMealScreen(
        state.toUiState(),
        viewModel::onLoadNextRandomMealClick
    ) { navController.navigate(NavScreen.CategoryList.route) }
}
