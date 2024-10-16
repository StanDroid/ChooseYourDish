package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.R
import com.cyd.core.navigation.NavScreen
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
    MealScaffold(stringResource(R.string.dish_of_the_day)) {
        RandomMealScreen(
            state,
            viewModel::onLoadNextRandomMealClick,
            { navController.navigate(NavScreen.CategoryList.route) },
            { navController.navigate(NavScreen.Search.route) },
            { navController.navigate(NavScreen.Favorites.route) },
            {
                navController.navigate(
                    NavScreen.MealDetails.withStringArgs(
                        it.first,
                        it.second
                    )
                )
            },
        )
    }
}