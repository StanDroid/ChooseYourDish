package com.cyd.core.navigation.route

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import com.cyd.search.SearchScreen
import com.cyd.search.SearchViewModel
import com.cyd.ui.view.base.MealScaffold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SearchRoute(
    navController: NavHostController
) {
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val searchState by searchViewModel.searchViewModelState.collectAsState()

    val mealsViewModel = hiltViewModel<MealListViewModel>()
    val mealsState by remember { mealsViewModel.uiState }

    CydBackHandler(navController)
    MealScaffold(
        stringResource(R.string.ingredients),
    ) {
        Column {
            SearchScreen(
                searchState,
                onSearchTextChange = searchViewModel::onSearchTextChange,
                onToggleSearch = searchViewModel::onToggleSearch,
                onItemClick = {
                    searchViewModel.onItemClick(it)
                    mealsViewModel.loadMeals(MealType.Ingredient(it.name))
                }
            )

            val isMealsEnabled by remember {
                derivedStateOf {
                    searchState.hasSelectedIngredient()
                }
            }
            if (isMealsEnabled) {
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
                    initLoading = { mealsViewModel.loadMeals(MealType.Ingredient(searchState.searchText)) }
                )
            }
        }
    }
}