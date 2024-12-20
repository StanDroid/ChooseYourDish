package com.cyd.core.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.feature.meal_details.MealDetailsScreen
import com.cyd.feature.meal_details.viewmodel.MealDetailsViewModel
import com.cyd.ui.view.base.MealScaffold

@Composable
fun MealDetailsRoute(
    id: String,
    name: String,
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<MealDetailsViewModel>()
    val state by remember { viewModel.uiState }
    MealScaffold(
        name,
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onIconClick = { navController.navigateUp() }) {
        MealDetailsScreen(
            state.toUiState(),
            loadMealDetailsAction = { viewModel.loadMealDetails(id) },
            tapOnFavoritesAction = { viewModel.tapOnFavorite() },
        )
    }
}