package com.cyd.feature.categories

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyd.base.model.Category
import com.cyd.base.viewmodel.UiState
import com.cyd.feature.categories.viewmodel.CategoriesViewModel
import com.cyd.ui.view.base.CategoryListScreenConstants.CATEGORY_LIST
import com.cyd.ui.view.meal.CategoryItemView
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun CategoryListScreen(
    viewModel: CategoriesViewModel,
    onCategoryClick: (Category) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AnimatedContent(uiState) { state ->
        when (state) {
            is UiState.InProgress -> {
                ProgressLoadingView()
            }

            is UiState.NoData -> {
                NoCategories()
            }

            is UiState.HasData -> {
                CategoryList(state.data, onCategoryClick)
            }
        }
    }
}

@Composable
private fun CategoryList(
    list: List<Category>,
    onCategoryClick: (Category) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.testTag(CATEGORY_LIST),
    ) {
        items(list, key = { it.id }) { category ->
            CategoryItemView(category, onCategoryClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoCategories() {
    Box(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.no_categories),
        )
    }
}
