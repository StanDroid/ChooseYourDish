package com.cyd.core.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyd.R
import com.cyd.core.navigation.NavScreen
import com.cyd.feature.categories.CategoryListScreen
import com.cyd.feature.categories.viewmodel.CategoriesViewModel
import com.cyd.ui.view.base.MealScaffold

@Composable
fun CategoryListRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<CategoriesViewModel>()
    val state by remember { viewModel.uiState }
    MealScaffold(
        stringResource(R.string.categories),
        icon = Icons.Default.ArrowBack,
        onIconClick = { navController.navigateUp() }
    ) {
        CategoryListScreen(
            state.toUiState()
        ) {
            navController.navigate(
                NavScreen.MealList.withStringArgs(
                    it.id,
                    it.name
                )
            )
        }
    }
}