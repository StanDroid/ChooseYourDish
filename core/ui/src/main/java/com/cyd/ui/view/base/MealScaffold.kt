package com.cyd.ui.view.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.base.utils.PRIVACY_POLICY
import com.cyd.ui.R
import com.cyd.ui.view.base.style.CydTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealScaffold(
    topBarText: String,
    icon: ImageVector? = null,
    onIconClick: () -> Unit = { },
    content: @Composable () -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = topBarText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (icon != null) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { onIconClick.invoke() }
                                .padding(16.dp)
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                actions = {
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.options)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        DropdownMenuItem(text = { Text(stringResource(R.string.privacy_policy)) },
                            onClick = {
                                expanded.value = false
                                uriHandler.openUri(PRIVACY_POLICY)
                            })
                    }
                }
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
    CydTheme {
        MealScaffold(topBarText = "Test Topbar", icon = Icons.Default.Home) {
            Column {
                Text(text = "test")
                Text(text = "test")
                Text(text = "test")
                Text(text = "test")
                Text(text = "test")
                Text(text = "test")
            }
        }
    }
}