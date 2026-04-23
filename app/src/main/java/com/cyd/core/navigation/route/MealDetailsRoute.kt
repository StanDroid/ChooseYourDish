package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cyd.feature.mealdetails.MealDetailsScreen
import com.cyd.feature.mealdetails.viewmodel.MealDetailsViewModel

@Composable
fun MealDetailsRoute(
    id: String,
    name: String,
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<MealDetailsViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadMealDetails(id)
    }

    MealDetailsScreen(
        uiState,
        tapOnFavoritesAction = { viewModel.tapOnFavorite() },
    )
}
