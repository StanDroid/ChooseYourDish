package com.cyd.core.navigation.route

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.core.navigation.Graph
import com.cyd.feature.random_meal.RandomMealScreen
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState
import com.cyd.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<RandomMealViewModel>()
    val state: RandomMealUiState by viewModel.uiState.collectAsState()
    val activity = LocalContext.current as? Activity
    BackHandler {
        if (activity != null) {
            activity.finish()
        } else {
            navController.popBackStack()
        }
    }
    RandomMealScreen(
        state,
        viewModel::onLoadNextRandomMealClick,
    ) {
        navController.navigate(
            Graph.MealDetailsScreen.withStringArgs(
                it.first,
                it.second
            )
        )
    }
}