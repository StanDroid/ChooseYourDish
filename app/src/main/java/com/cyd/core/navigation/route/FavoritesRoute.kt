package com.cyd.core.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.R
import com.cyd.core.navigation.NavScreen
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

    MealScaffold(
        stringResource(R.string.favorites),
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onIconClick = { navController.navigateUp() }) {
        MealListScreen(
            mealsState.toUiState(),
            onMealClick = {
                navController.navigate(
                    NavScreen.MealDetails.withStringArgs(
                        it.id,
                        it.name
                    )
                )
            },
            initLoading = { mealsViewModel.loadMeals(MealType.Favorites) }
        )
    }
}