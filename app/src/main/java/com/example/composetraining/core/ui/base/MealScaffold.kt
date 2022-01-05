package com.example.composetraining.core.ui.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MealScaffold(
    topBarText: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topBarText) },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = content
    )
}

@Preview
@Composable
fun MealScaffoldPreview() {
    MealScaffold(topBarText = "Test Topbar") {}
}