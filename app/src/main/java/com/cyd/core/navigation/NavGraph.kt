package com.cyd.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cyd.core.navigation.route.CategoryListRoute
import com.cyd.core.navigation.route.MealDetailsRoute
import com.cyd.core.navigation.route.MealListRoute
import com.cyd.core.navigation.route.RandomMealRoute

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavigationSystem() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.RandomMeal.route,
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        }
    ) {
        composable(NavScreen.RandomMeal.route) {
            RandomMealRoute(navController)
        }
        composable(NavScreen.CategoryList.route) {
            CategoryListRoute(navController)
        }
        composable(
            route = NavScreen.MealList.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(
                navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType }
            )
        ) {
            it.arguments?.let { args ->
                MealListRoute(
                    args.getString(PARAM_ID).orEmpty(),
                    args.getString(PARAM_NAME).orEmpty(),
                    navController
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
                MealDetailsRoute(
                    args.getString(PARAM_ID).orEmpty(),
                    args.getString(PARAM_NAME).orEmpty(),
                    navController,
                )
            }
        }
    }
}

const val PARAM_ID = "id"
const val PARAM_NAME = "name"