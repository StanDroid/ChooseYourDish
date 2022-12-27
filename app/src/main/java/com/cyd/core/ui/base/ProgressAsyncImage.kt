package com.cyd.core.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Dimension
import coil.size.Size
import coil.transform.Transformation
import com.cyd.R

@Composable
fun ProgressAsyncImage(
    model: String,
    modifier: Modifier = Modifier,
    withLoadingIndicator: Boolean = true,
    transformation: Transformation? = null
) {
    val density = LocalDensity.current
    val size = (32 * density.density).toInt()
    val gif = getGitPainter(
        Size(
            Dimension.Pixels(size),
            Dimension.Pixels(size)
        )
    )
    val builder = ImageRequest.Builder(LocalContext.current)
        .data(model)
        .crossfade(true)
    transformation?.let {
        builder.transformations(it)
    }
    AsyncImage(
        model = builder.build(),
        placeholder = if (withLoadingIndicator) gif else null,
        error = painterResource(id = R.drawable.no_data_found),
        contentScale = ContentScale.Inside,
        contentDescription = "",
        modifier = modifier
    )
}