package com.cyd.core.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.cyd.R

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
            if (withLoadingIndicator) GifImage(modifier = Modifier.padding(16.dp))
        },
        error = {
            Image(
                modifier = Modifier.alpha(0.3f).padding(16.dp),
                painter = painterResource(id = R.drawable.no_data_found),
                contentDescription = null
            )
        },
        contentScale = ContentScale.Inside,
        contentDescription = "",
        modifier = modifier
    )
}