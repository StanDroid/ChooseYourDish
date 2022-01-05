package com.example.composetraining.core.ui.memes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.composetraining.core.data.model.memes.MemeModel

@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoilApi
@Composable
fun ItemList(list: List<MemeModel>, onClickAction: (MemeModel) -> Unit) {
    val sections = listOf("A","B","C")
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        sections.forEach { item->
            stickyHeader {
                Text(text = "Sticky Text $item")
            }
            items(list) { MemeItem(model = it, onClickAction) }
        }
    }
}

@ExperimentalMaterialApi
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
