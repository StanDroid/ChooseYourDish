package com.example.composetraining.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.core.data.model.MemeModel

@Composable
fun ItemList(list: List<MemeModel>, onClickAction: (MemeModel) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
    ) {
        items(list) { MemeItem(model = it, onClickAction) }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun ShowMemeItemList() {
    ItemList(listOf(
        MemeModel("Test 1"),
        MemeModel("Test 2")
    ),
        onClickAction = {})
}
