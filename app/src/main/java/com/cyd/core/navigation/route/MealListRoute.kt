package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.base.model.MealItem
import com.cyd.feature.category_meals.MealListScreen
import com.cyd.feature.category_meals.viewmodel.MealListViewModel
import com.cyd.feature.category_meals.viewmodel.MealType

@Composable
fun MealListRoute(
    name: String,
    navController: NavHostController,
    onMealClick: (MealItem) -> Unit,
) {
    val viewModel = hiltViewModel<MealListViewModel>()
    val state by remember { viewModel.uiState }
    MealListScreen(
        state.toUiState(),
        onMealClick = onMealClick,
        initLoading = { viewModel.loadMeals(MealType.Category(name)) }
    )
}