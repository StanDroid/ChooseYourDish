package com.cyd.ui.view.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MealScaffold(
    topBarText: String,
    icon: ImageVector = Icons.Default.Home,
    onIconClick: () -> Unit = { },
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topBarText) },
                navigationIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onIconClick.invoke() }
                            .padding(16.dp)
                    )
                },
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