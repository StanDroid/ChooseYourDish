package com.cyd.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cyd.base.model.Ingredient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchViewModel.SearchViewModelState,
    onSearchTextChange: (String) -> Unit,
    onToggleSearch: () -> Unit,
    onItemClick: (Ingredient) -> Unit
) {
    Column() {
        SearchBar(
            query = state.searchText,
            onQueryChange = onSearchTextChange::invoke,
            onSearch = onSearchTextChange::invoke,
            active = state.isSearching,
            onActiveChange = { onToggleSearch.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(state.list) { item ->
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onItemClick(item) },
                    )
                }
            }
        }
    }
}