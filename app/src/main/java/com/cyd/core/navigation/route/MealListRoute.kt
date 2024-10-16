package com.cyd.core.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.core.navigation.Screen
import com.cyd.feature.category_meals.MealListScreen
import com.cyd.feature.category_meals.viewmodel.MealListViewModel
import com.cyd.feature.category_meals.viewmodel.MealType
import com.cyd.ui.view.base.MealScaffold

@Composable
fun MealListRoute(
    id: String,
    name: String,
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<MealListViewModel>()
    val state by remember { viewModel.uiState }
    MealScaffold(name,
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onIconClick = { navController.navigateUp() }) {
        MealListScreen(
            state.toUiState(),
            onMealClick = {
                navController.navigate(
                    Screen.MealDetails.withStringArgs(
                        it.id,
                        it.name
                    )
                )
            },
            initLoading = { viewModel.loadMeals(MealType.Category(name)) }
        )
    }
}