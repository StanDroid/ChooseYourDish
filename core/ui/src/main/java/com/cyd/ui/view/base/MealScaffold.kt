package com.cyd.ui.view.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealScaffold(
    topBarText: String,
    icon: ImageVector = Icons.Default.Home,
    onIconClick: () -> Unit = { },
    content: @Composable () -> Unit
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
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
            )
        },
        content = {
            Surface(modifier = Modifier.padding(it)) {
                content.invoke()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MealScaffoldPreview() {
    MealScaffold(topBarText = "Test Topbar") {
        Column() {
            Text(text = "test")
            Text(text = "test")
            Text(text = "test")
            Text(text = "test")
            Text(text = "test")
            Text(text = "test")
        }
    }
}