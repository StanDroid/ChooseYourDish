package com.cyd.core.navigation.route

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.core.navigation.NavScreen
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
    val viewModel = hiltViewModel<SearchViewModel>()
    val state by viewModel.searchViewModelState.collectAsState()

    val mealsViewModel = hiltViewModel<MealListViewModel>()
    val mealsState by remember { mealsViewModel.uiState }

    MealScaffold(
        "Ingredients",
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onIconClick = { navController.navigateUp() }) {
        Column {
            SearchScreen(
                state,
                onSearchTextChange = viewModel::onSearchTextChange,
                onToggleSearch = viewModel::onToggleSearch,
                onItemClick = viewModel::onItemClick
            )
//            val state by derivedStateOf {
//                state.searchText.isNotEmpty() && !state.isSearching
//            }

            val isMealsEnabled by remember {
                derivedStateOf {
                    state.searchText.isNotEmpty() && !state.isSearching
                }
            }
            if (isMealsEnabled) {
                MealListScreen(
                    "id",
                    "name",
                    mealsState.toUiState(),
                    onMealClick = {
                        navController.navigate(
                            NavScreen.MealDetails.withStringArgs(
                                it.id,
                                it.name
                            )
                        )
                    },
                    initLoading = { mealsViewModel.loadMeals(MealType.Ingredient(state.searchText)) }
                )
            }
        }
    }
}