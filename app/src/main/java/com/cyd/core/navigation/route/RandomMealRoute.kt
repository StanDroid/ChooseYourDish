package com.cyd.core.navigation.route

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.base.model.RandomMeal
import com.cyd.base.viewmodel.UiState
import com.cyd.core.navigation.Graph
import com.cyd.feature.randommeal.RandomMealScreen
import com.cyd.feature.randommeal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<RandomMealViewModel>()
    val state: UiState<RandomMeal> by viewModel.uiState.collectAsState()
    val activity = LocalContext.current as? Activity
    BackHandler {
        if (activity != null) {
            activity.finish()
        } else {
            navController.popBackStack()
        }
    }
    RandomMealScreen(
        uiState = state,
        onLoadNextRandomMeal = viewModel::onLoadNextRandomMealClick,
    ) {
        navController.navigate(
            Graph.MealDetailsScreen.withStringArgs(
                it.first,
                it.second,
            ),
        )
    }
}
