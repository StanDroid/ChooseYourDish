package com.example.composetraining.core.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.composetraining.core.data.model.MemeModel

@Composable
fun ItemList(list: List<MemeModel>, onClickAction: (MemeModel) -> Unit) {
    LazyColumn() {
        list.forEach {
            item {
                MemeItem(model = it, onClickAction)
            }
        }
    }
}