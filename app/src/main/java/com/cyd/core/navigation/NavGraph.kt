@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyd.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
    Scaffold(
        bottomBar = {
            if (currentRoute(navController) != NavScreen.Splash.route) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavScreen.Splash.route,
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
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
            composable(BottomBarScreen.RandomMeal.route) {
                RandomMealRoute(navController)
            }
            composable(BottomBarScreen.CategoryList.route) {
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
            composable(BottomBarScreen.Search.route) {
                SearchRoute(navController)
            }
            composable(BottomBarScreen.Favorites.route) {
                FavouritesRoute(navController)
            }
        }
    }
}

val items: List<BottomBarScreen> = listOf(
    BottomBarScreen.RandomMeal,
    BottomBarScreen.CategoryList,
    BottomBarScreen.Search,
    BottomBarScreen.Favorites,
)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = currentRoute(navController)
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        screen.icon, contentDescription = screen.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

const val PARAM_ID = "id"
const val PARAM_NAME = "name"