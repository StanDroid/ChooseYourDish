package com.cyd.ui.view.meal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.ui.view.base.GifImage

@Composable
fun ProgressLoadingView() {
    Box(
        Modifier
            .fillMaxSize()
            .testTag("progress_loading"),
        contentAlignment = Alignment.Center,
    ) {
        GifImage(Modifier.size(70.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressLoadingViewPreview() {
    ProgressLoadingView()
}
