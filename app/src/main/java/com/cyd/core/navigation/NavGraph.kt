@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyd.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.cyd.core.navigation.route.FavouritesRoute
import com.cyd.core.navigation.route.MealDetailsRoute
import com.cyd.core.navigation.route.MealListRoute
import com.cyd.core.navigation.route.RandomMealRoute
import com.cyd.core.navigation.route.SearchRoute
import com.cyd.core.navigation.route.SplashScreenRoute

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavigationSystem() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.Splash.route,
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        enterTransition = {
            fadeIn(
                animationSpec = tween(durationMillis = 500),
                initialAlpha = 0.5f
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = 500),
                targetAlpha = 0.5f
            )
        },
    ) {
        composable(route = NavScreen.Splash.route) {
            SplashScreenRoute(navController)
        }
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
        composable(NavScreen.Search.route) {
            SearchRoute(navController)
        }
        composable(NavScreen.Favorites.route) {
            FavouritesRoute(navController)
        }
    }
}

const val PARAM_ID = "id"
const val PARAM_NAME = "name"