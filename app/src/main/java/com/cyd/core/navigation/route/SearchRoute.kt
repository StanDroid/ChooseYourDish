package com.cyd.core.navigation.route

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cyd.core.navigation.CydBackHandler
import com.cyd.core.navigation.Graph
import com.cyd.feature.categorymeals.MealListScreen
import com.cyd.feature.categorymeals.viewmodel.MealListViewModel
import com.cyd.feature.categorymeals.viewmodel.MealType
import com.cyd.search.SearchScreen
import com.cyd.search.viewmodel.SearchViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SearchRoute(navController: NavHostController) {
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val searchState by searchViewModel.viewModelState.collectAsStateWithLifecycle()

    val mealsViewModel = hiltViewModel<MealListViewModel>()
    val mealsState by mealsViewModel.uiState.collectAsStateWithLifecycle()

    CydBackHandler(navController)
    Column {
        SearchScreen(
            searchState,
            onSearchTextChange = searchViewModel::onSearchTextChange,
            onToggleSearch = searchViewModel::onToggleSearch,
            onItemClick = {
                searchViewModel.onItemClick(it)
                mealsViewModel.loadMeals(MealType.Ingredient(it.name))
            },
        )

        val isMealsEnabled by remember {
            derivedStateOf {
                searchState.hasSelectedIngredient()
            }
        }

        LaunchedEffect(Unit) {
            mealsViewModel.loadMeals(MealType.Ingredient(searchState.searchText))
        }

        if (isMealsEnabled) {
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
    }
}
