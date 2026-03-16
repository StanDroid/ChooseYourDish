package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cyd.base.model.MealItem
import com.cyd.feature.categorymeals.MealListScreen
import com.cyd.feature.categorymeals.viewmodel.MealListViewModel
import com.cyd.feature.categorymeals.viewmodel.MealType

@Composable
fun MealListRoute(
    name: String,
    navController: NavHostController,
    onMealClick: (MealItem) -> Unit,
) {
    val viewModel = hiltViewModel<MealListViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadMeals(MealType.Category(name))
    }

    MealListScreen(
        state,
        onMealClick = onMealClick,
    )
}
