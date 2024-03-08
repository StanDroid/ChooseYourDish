package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cyd.core.navigation.NavScreen
import com.cyd.feature.random_meal.RandomMealScreen
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState
import com.cyd.feature.random_meal.viewmodel.RandomMealViewModel
import com.cyd.ui.view.base.MealScaffold

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RandomMealRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<RandomMealViewModel>()
    val state: RandomMealUiState by viewModel.uiState.collectAsStateWithLifecycle()
    MealScaffold("Dish Of The Day") {
        RandomMealScreen(
            state,
            viewModel::onLoadNextRandomMealClick
        ) { navController.navigate(NavScreen.CategoryList.route) }
    }
}