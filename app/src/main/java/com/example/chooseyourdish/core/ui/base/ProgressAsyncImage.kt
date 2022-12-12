package com.example.chooseyourdish.core.ui.base

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation

@Composable
fun ProgressAsyncImage(
    model: String,
    modifier: Modifier = Modifier,
    withLoadingIndicator: Boolean = true
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(model)
            .transformations(RoundedCornersTransformation(4f))
            .crossfade(true)
            .build(),
        loading = {
            if (withLoadingIndicator) CircularProgressIndicator(modifier = Modifier.size(50.dp))
        },
        contentScale = ContentScale.Inside,
        contentDescription = "",
        modifier = modifier
    )
}