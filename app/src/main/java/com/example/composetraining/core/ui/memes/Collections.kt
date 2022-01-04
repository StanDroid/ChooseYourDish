package com.example.composetraining.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.composetraining.core.data.model.memes.MemeModel

@ExperimentalCoilApi
@Composable
fun ItemList(list: List<MemeModel>, onClickAction: (MemeModel) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { MemeItem(model = it, onClickAction) }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun ShowMemeItemList() {
    ItemList(listOf(
        MemeModel("Test 1","",""),
        MemeModel("Test 2","","")
    ),
        onClickAction = {})
}
