package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.R
import com.cyd.core.navigation.CydBackHandler
import com.cyd.core.navigation.Graph
import com.cyd.feature.category_meals.MealListScreen
import com.cyd.feature.category_meals.viewmodel.MealListViewModel
import com.cyd.feature.category_meals.viewmodel.MealType
import com.cyd.ui.view.base.MealScaffold

@Composable
fun FavouritesRoute(
    navController: NavHostController
) {
    val mealsViewModel = hiltViewModel<MealListViewModel>()
    val mealsState by remember { mealsViewModel.uiState }
    CydBackHandler(navController)
    MealScaffold(
        stringResource(R.string.favorites),
    ) {
        MealListScreen(
            mealsState.toUiState(),
            onMealClick = {
                navController.navigate(
                    Graph.MealDetailsScreen.withStringArgs(
                        it.id,
                        it.name
                    )
                )
            },
            initLoading = { mealsViewModel.loadMeals(MealType.Favorites) }
        )
    }
}