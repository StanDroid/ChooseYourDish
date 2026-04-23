@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.cyd.ui.view.base

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Dimension
import coil.size.Size
import coil.transform.Transformation
import com.cyd.ui.R

private const val LOADING_INDICATOR_SIZE_DP = 32

@Composable
fun ProgressAsyncImage(
    model: String,
    modifier: Modifier = Modifier,
    withLoadingIndicator: Boolean = true,
    transformation: Transformation? = null,
    contentDescription: String? = null,
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val imageRequest = remember(model, transformation, context) {
        ImageRequest.Builder(context)
            .data(model)
            .memoryCacheKey(model)
            .crossfade(true)
            .apply {
                transformation?.let { transformations(it) }
            }
            .build()
    }

    val loadingSizePx = remember(density) {
        with(density) { LOADING_INDICATOR_SIZE_DP.dp.roundToPx() }
    }

    val placeholderPainter = if (withLoadingIndicator) {
        getGitPainter(
            Size(
                Dimension.Pixels(loadingSizePx),
                Dimension.Pixels(loadingSizePx)
            )
        )
    } else {
        null
    }

    AsyncImage(
        model = imageRequest,
        placeholder = placeholderPainter,
        error = painterResource(id = R.drawable.no_data_found),
        contentScale = ContentScale.Inside,
        contentDescription = contentDescription,
        modifier = modifier
    )
}