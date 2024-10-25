@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyd.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
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
    val items = mainTabs
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            AnimatedVisibility(isBottomNavBarVisible(currentBackStackEntry)) {
                BottomNavBar(items, navController)
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Graph.SplashGraph.route,
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
                    testTagsAsResourceId = true
                },
            enterTransition = {
                fadeIn(
                    animationSpec = tween(durationMillis = 500), initialAlpha = 0.5f
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(durationMillis = 500), targetAlpha = 0.5f
                )
            },
        ) {
            navigation(
                route = Graph.SplashGraph.route,
                startDestination = Graph.SplashGraph.SplashScreen.route
            ) {
                composable(Graph.SplashGraph.SplashScreen.route) {
                    SplashScreenRoute(navController)
                }
            }
            addHomeRoute(navController)
            addCategoriesRoute(navController)
            addSearchRoute(navController)
            addFavoritesRoute(navController)

            composable(
                route = Graph.MealDetailsScreen.route + "/{$PARAM_ID}/{$PARAM_NAME}",
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
}

@Composable
private fun BottomNavBar(
    items: List<AppTab>,
    navController: NavHostController
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val closestNavGraphDestination = currentBackStackEntry?.destination?.hierarchy?.first {
        it is NavGraph
    }
    NavigationBar {
        val currentTab = items.firstOrNull { it.graph == closestNavGraphDestination?.route }
        items.forEach { tab ->
            NavigationBarItem(icon = { Icon(tab.icon, contentDescription = null) },
                label = {
                    Text(
                        text = tab.title,
                        maxLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                selected = currentTab == tab,
                onClick = {
                    if (currentTab != null) {
                        if (tab != currentTab) {
                            navController.navigate(tab.graph) {
                                popUpTo(currentTab.graph) {
                                    saveState = true
                                    inclusive = true
                                }
                                restoreState = true
                            }
                        } else {
                            navController.navigateToRootScreen(
                                tab.graph,
                                closestNavGraphDestination
                            )
                        }
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.addHomeRoute(
    navController: NavHostController,
) {
    navigation(
        startDestination = Graph.HomeGraph.HomeScreen.route,
        route = Graph.HomeGraph.route
    ) {
        composable(Graph.HomeGraph.HomeScreen.route) {
            RandomMealRoute(navController)
        }
    }
}

private fun NavGraphBuilder.addCategoriesRoute(
    navController: NavHostController,
) {
    navigation(
        route = Graph.CategoriesGraph.route,
        startDestination = Graph.CategoriesGraph.CategoryListScreen.route
    ) {
        composable(Graph.CategoriesGraph.CategoryListScreen.route) {
            CategoryListRoute(navController)
        }
        composable(
            route = Graph.CategoriesGraph.MealListScreen.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType })
        ) {
            it.arguments?.let { args ->
                MealListRoute(
                    args.getString(PARAM_NAME).orEmpty(), navController
                ) { navigateArgs ->
                    navController.navigate(
                        Graph.MealDetailsScreen.withStringArgs(
                            navigateArgs.id, navigateArgs.name
                        )
                    )
                }
            }
        }
    }
}

private fun NavGraphBuilder.addSearchRoute(
    navController: NavHostController,
) {
    navigation(
        route = Graph.SearchGraph.route,
        startDestination = Graph.SearchGraph.SearchScreen.route,
    ) {
        composable(Graph.SearchGraph.SearchScreen.route) {
            SearchRoute(navController)
        }
    }
}

private fun NavGraphBuilder.addFavoritesRoute(
    navController: NavHostController,
) {
    navigation(
        route = Graph.FavoritesGraph.route,
        startDestination = Graph.FavoritesGraph.FavoritesScreen.route
    ) {
        composable(Graph.FavoritesGraph.FavoritesScreen.route) {
            FavouritesRoute(navController)
        }
    }
}

private fun NavHostController.navigateToRootScreen(
    rootScreen: String,
    closestNavGraphDestination: NavDestination?
) {
    val dest = (closestNavGraphDestination as NavGraph).findStartDestination().route
    if (dest != currentDestination?.route) {
        navigate(rootScreen) {
            restoreState = true
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
        }
    }
}

private fun isBottomNavBarVisible(currentBackStackEntry: NavBackStackEntry?) =
    currentBackStackEntry != null && getSimpleRoute(currentBackStackEntry) !in setOf(
        Graph.MealDetailsScreen.route,
        Graph.SplashGraph.SplashScreen.route
    )

private fun getSimpleRoute(currentBackStackEntry: NavBackStackEntry?) =
    currentBackStackEntry?.destination?.route?.split("/")?.first()


const val PARAM_ID = "id"
const val PARAM_NAME = "name"