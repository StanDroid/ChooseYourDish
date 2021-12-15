package com.example.composetraining.core.ui

import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.composetraining.core.data.model.MemeModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemeItem(model: MemeModel, onClicked: (MemeModel) -> Unit) {
    Card(
        backgroundColor = Color.LightGray,
        onClick = { onClicked.invoke(model) }
    ) {
        Text(text = "Hello ${model.name}!")
    }
}

@Composable
fun MemeItemDetailed(model: MemeModel) {
    Text(text = "Hello ${model.name}!")
}
