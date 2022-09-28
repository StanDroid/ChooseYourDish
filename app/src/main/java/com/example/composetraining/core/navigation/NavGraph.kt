package com.example.composetraining.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetraining.core.navigation.route.CategoryListRoute
import com.example.composetraining.core.navigation.route.MealDetailsRoute
import com.example.composetraining.core.navigation.route.MealListRoute
import com.example.composetraining.core.navigation.route.RandomMealRoute
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesViewModel
import com.example.composetraining.feature.meal_details.viewmodel.MealDetailsViewModel
import com.example.composetraining.feature.meal_details.viewmodel.MealListViewModel
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun NavigationSystem() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.RandomMeal.route) {
        composable(NavScreen.RandomMeal.route) {
            val viewModel = hiltViewModel<RandomMealViewModel>()
            RandomMealRoute(navController, viewModel)
        }
        composable(NavScreen.CategoryList.route) {
            val viewModel = hiltViewModel<CategoriesViewModel>()
            CategoryListRoute(navController, viewModel)
        }
        composable(
            route = NavScreen.MealList.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(
                navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType }
            )
        ) {
            it.arguments?.let { args ->
                val viewModel = hiltViewModel<MealListViewModel>()
                MealListRoute(
                    args.getString(PARAM_ID).orEmpty(),
                    args.getString(PARAM_NAME).orEmpty(),
                    navController,
                    viewModel
                )
            }
        }
        composable(
            route = NavScreen.MealDetails.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(
                navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType }
            )
        ) {
            it.arguments?.let { args ->
                val viewModel = hiltViewModel<MealDetailsViewModel>()
                MealDetailsRoute(
                    args.getString(PARAM_ID).orEmpty(),
                    args.getString(PARAM_NAME).orEmpty(),
                    navController,
                    viewModel
                )
            }
        }
    }
}

const val PARAM_ID = "id"
const val PARAM_NAME = "name"