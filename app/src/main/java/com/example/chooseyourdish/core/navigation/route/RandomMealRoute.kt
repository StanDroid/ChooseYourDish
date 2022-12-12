package com.example.chooseyourdish.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chooseyourdish.core.navigation.NavScreen
import com.example.chooseyourdish.feature.random_meal.RandomMealScreen
import com.example.chooseyourdish.feature.random_meal.viewmodel.RandomMealUiState
import com.example.chooseyourdish.feature.random_meal.viewmodel.RandomMealViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RandomMealRoute(
    navController: NavHostController,
    viewModel: RandomMealViewModel
) {
    val state: RandomMealUiState by viewModel.uiState.collectAsStateWithLifecycle()

    RandomMealScreen(
        state,
        viewModel::onLoadNextRandomMealClick
    ) { navController.navigate(NavScreen.CategoryList.route) }
}
