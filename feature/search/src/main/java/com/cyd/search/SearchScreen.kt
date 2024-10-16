package com.cyd.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.base.model.Ingredient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchViewModel.SearchViewModelState,
    onSearchTextChange: (String) -> Unit,
    onToggleSearch: () -> Unit,
    onItemClick: (Ingredient) -> Unit,
) {
    Column {
        SearchBar(
            query = state.searchText,
            onQueryChange = onSearchTextChange::invoke,
            onSearch = onSearchTextChange::invoke,
            active = state.isSearching,
            placeholder = { Text(stringResource(R.string.search_some_ingredient)) },
            trailingIcon = {
                if (state.searchText.isNotEmpty()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onSearchTextChange.invoke("")
                        })
                }
            },
            onActiveChange = { onToggleSearch.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            LazyColumn {
                items(state.list) { item ->
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .clickable { onItemClick(item) }
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(state = SearchViewModel.SearchViewModelState(
        searchText = "RET",
        isSearching = true,
        list = listOf(
            Ingredient("RET", "RET", "RET"),
            Ingredient("RET", "RET", "RET"),
            Ingredient("RET", "RET", "RET"),
            Ingredient("RET", "RET", "RET"),
            Ingredient("RET", "RET", "RET"),
        )
    ),
        {},
        {},
        {}
    )
}