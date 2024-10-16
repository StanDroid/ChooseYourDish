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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
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
    val currentSelectedScreen by navController.currentScreenAsState()
    Scaffold(
        bottomBar = {
            if (currentRoute(navController) != Screen.Splash.route) {
                BottomNavigationBar(navController, currentSelectedScreen)
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GraphRoute.Splash.route,
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
                    testTagsAsResourceId = true
                },
//            enterTransition = {
//                fadeIn(
//                    animationSpec = tween(durationMillis = 500), initialAlpha = 0.5f
//                )
//            },
//            exitTransition = {
//                fadeOut(
//                    animationSpec = tween(durationMillis = 500), targetAlpha = 0.5f
//                )
//            },
        ) {
            addSplashRoute(navController)
            addHomeRoute(navController)
            addCategoriesRoute(navController)
            addSearchRoute(navController)
            addFavoritesRoute(navController)
        }
    }
}

fun NavGraphBuilder.addSplashRoute(
    navController: NavHostController,
) {
    navigation(
        route = GraphRoute.Splash.route,
        startDestination = Screen.Splash.route,
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreenRoute(navController)
        }
    }
}

fun NavGraphBuilder.addHomeRoute(
    navController: NavHostController,
) {
    navigation(
        route = GraphRoute.Home.route,
        startDestination = BottomBarScreen.RandomMeal.route,
    ) {
        composable(route = BottomBarScreen.RandomMeal.route) {
            RandomMealRoute(navController)
        }
    }
}

fun NavGraphBuilder.addCategoriesRoute(
    navController: NavHostController,
) {
    navigation(
        route = GraphRoute.Categories.route,
        startDestination = BottomBarScreen.CategoryList.route
    ) {
        composable(route = BottomBarScreen.CategoryList.route) {
            CategoryListRoute(navController)
        }
        composable(
            route = Screen.MealList.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType })
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
            route = Screen.MealDetails.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType })
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

fun NavGraphBuilder.addSearchRoute(
    navController: NavHostController,
) {
    navigation(
        route = GraphRoute.Search.route,
        startDestination = BottomBarScreen.Search.route,
    ) {
        composable(BottomBarScreen.Search.route) {
            SearchRoute(navController)
        }
    }
}

fun NavGraphBuilder.addFavoritesRoute(
    navController: NavHostController,
) {
    navigation(
        startDestination = BottomBarScreen.Favorites.route,
        route = GraphRoute.Favorites.route
    ) {
        composable(BottomBarScreen.Favorites.route) {
            FavouritesRoute(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentSelectedScreen: GraphRoute,
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentSelectedScreen == GraphRoute.Home,
            onClick = { navController.navigateToRootScreen(GraphRoute.Home) },
            alwaysShowLabel = true,
            icon = {
                Icon(
                    BottomBarScreen.RandomMeal.icon,
                    contentDescription = BottomBarScreen.RandomMeal.title,
                    modifier = Modifier.size(24.dp)
                )
            },
        )
        NavigationBarItem(
            selected = currentSelectedScreen == GraphRoute.Categories,
            onClick = { navController.navigateToRootScreen(GraphRoute.Categories) },
            alwaysShowLabel = true,
            icon = {
                Icon(
                    BottomBarScreen.CategoryList.icon,
                    contentDescription = BottomBarScreen.CategoryList.title,
                    modifier = Modifier.size(24.dp)
                )
            },
        )
        NavigationBarItem(
            selected = currentSelectedScreen == GraphRoute.Search,
            onClick = { navController.navigateToRootScreen(GraphRoute.Search) },
            alwaysShowLabel = true,
            icon = {
                Icon(
                    BottomBarScreen.Search.icon,
                    contentDescription = BottomBarScreen.Search.title,
                    modifier = Modifier.size(24.dp)
                )
            },
        )
        NavigationBarItem(
            selected = currentSelectedScreen == GraphRoute.Favorites,
            onClick = { navController.navigateToRootScreen(GraphRoute.Favorites) },
            alwaysShowLabel = true,
            icon = {
                Icon(
                    BottomBarScreen.Favorites.icon,
                    contentDescription = BottomBarScreen.Favorites.title,
                    modifier = Modifier.size(24.dp)
                )
            },
        )
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<GraphRoute> {
    val selectedItem = remember { mutableStateOf<GraphRoute>(GraphRoute.Home) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == GraphRoute.Home.route } -> {
                    selectedItem.value = GraphRoute.Home
                }

                destination.hierarchy.any { it.route == GraphRoute.Categories.route } -> {
                    selectedItem.value = GraphRoute.Categories
                }

                destination.hierarchy.any { it.route == GraphRoute.Search.route } -> {
                    selectedItem.value = GraphRoute.Search
                }

                destination.hierarchy.any { it.route == GraphRoute.Favorites.route } -> {
                    selectedItem.value = GraphRoute.Favorites
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

private fun NavController.navigateToRootScreen(rootScreen: GraphRoute) {
    navigate(rootScreen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}

const val PARAM_ID = "id"
const val PARAM_NAME = "name"