package com.cyd.core.navigation.route

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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

    MealScaffold(
        "Ingredients",
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onIconClick = { navController.navigateUp() }) {
        SearchScreen(
            state,
            onSearchTextChange = viewModel::onSearchTextChange,
            onToggleSearch = viewModel::onToggleSearch,
            onItemClick = viewModel::onItemClick
        )
    }
}