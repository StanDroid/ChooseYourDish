package com.cyd.core.navigation.route

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.R
import com.cyd.core.navigation.Graph
import com.cyd.feature.random_meal.RandomMealScreen
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState
import com.cyd.feature.random_meal.viewmodel.RandomMealViewModel
import com.cyd.ui.view.base.MealScaffold

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

    MealScaffold(stringResource(R.string.dish_of_the_day)) {
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
}