package com.cyd.core.navigation.route

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cyd.base.model.RandomMeal
import com.cyd.base.viewmodel.UiState
import com.cyd.core.navigation.Graph
import com.cyd.feature.randommeal.RandomMealScreen
import com.cyd.feature.randommeal.viewmodel.RandomMealViewModel

@Composable
fun RandomMealRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<RandomMealViewModel>()
    val state: UiState<RandomMeal> by viewModel.uiState.collectAsStateWithLifecycle()
    val activity = LocalActivity.current
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
        onAskAiClick = {
            navController.navigate(Graph.AiChatGraph.route)
        },
        onClickGoToMealDetails = {
            navController.navigate(
                Graph.MealDetailsScreen.withStringArgs(
                    it.first,
                    it.second,
                ),
            )
        },
    )
}
