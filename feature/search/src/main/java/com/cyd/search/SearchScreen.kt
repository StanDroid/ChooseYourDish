package com.cyd.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchViewModel.SearchViewModelState,
    onSearchTextChange: (String) -> Unit,
    onToggleSearch: () -> Unit,
    onCountryClick: (String) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = state.searchText,//text showed on SearchBar
            onQueryChange = onSearchTextChange::invoke, //update the value of searchText
            onSearch = onSearchTextChange::invoke, //the callback to be invoked when the input service triggers the ImeAction.Search action
            active = state.isSearching, //whether the user is searching or not
            onActiveChange = { onToggleSearch.invoke() }, //the callback to be invoked when this search bar's active state is changed
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(state.countriesList) { country ->
                    Text(
                        text = country,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onCountryClick(country) },
                    )
                }
            }
        }
        Text("Selected Country: ${state.searchText}")
    }
}