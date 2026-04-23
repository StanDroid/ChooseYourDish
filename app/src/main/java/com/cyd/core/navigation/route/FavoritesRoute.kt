package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cyd.core.navigation.CydBackHandler
import com.cyd.core.navigation.Graph
import com.cyd.feature.categorymeals.MealListScreen
import com.cyd.feature.categorymeals.viewmodel.MealListViewModel
import com.cyd.feature.categorymeals.viewmodel.MealType

@Composable
fun FavouritesRoute(navController: NavHostController) {
    val mealsViewModel = hiltViewModel<MealListViewModel>()
    val mealsState by mealsViewModel.uiState.collectAsStateWithLifecycle()
    CydBackHandler(navController)

    LaunchedEffect(Unit) {
        mealsViewModel.loadMeals(MealType.Favorites)
    }

    MealListScreen(
        mealsState,
        onMealClick = {
            navController.navigate(
                Graph.MealDetailsScreen.withStringArgs(
                    it.id,
                    it.name,
                ),
            )
        },
    )
}
