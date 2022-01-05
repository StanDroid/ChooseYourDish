package com.example.composetraining.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetraining.core.ui.meal.screen.CategoryListScreen
import com.example.composetraining.core.ui.meal.screen.RandomMealScreen
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesViewModel
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun NavigationSystem() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.RandomMeal.route) {
        composable(NavScreen.RandomMeal.route) {
            val viewModel: RandomMealViewModel =
                hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
            RandomMealScreen(navController, viewModel)
        }
        composable(NavScreen.CategoryList.route){
            val viewModel: CategoriesViewModel =
                hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
            CategoryListScreen(navController, viewModel)
        }
    }
}