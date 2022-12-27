package com.cyd.core.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.core.ui.base.MealScaffold
import com.cyd.feature.meal_details.MealDetailsScreen
import com.cyd.feature.meal_details.viewmodel.MealDetailsViewModel

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
        icon = Icons.Default.ArrowBack,
        onIconClick = { navController.navigateUp() }) {
        MealDetailsScreen(id,
            name,
            state.toUiState(),
            loadMealDetailsAction = { viewModel.loadMealDetails(id) })
    }
}