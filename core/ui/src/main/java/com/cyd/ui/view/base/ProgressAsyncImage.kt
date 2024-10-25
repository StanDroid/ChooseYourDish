@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.cyd.ui.view.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import com.cyd.ui.R

private const val LOADING_INDICATOR_SIZE_DP = 32

/**
 * TODO optimize with memory cache
 * .placeholderMemoryCacheKey(model)
 * .memoryCacheKey(model)
 *
 */
@Composable
fun ProgressAsyncImage(
    model: String,
    modifier: Modifier = Modifier,
    withLoadingIndicator: Boolean = true,
    transformation: Transformation? = null,
    contentDescription: String? = null
) {
    val des = LocalDensity.current
    val loadingIndicatorSize by remember {
        mutableIntStateOf(LOADING_INDICATOR_SIZE_DP * des.density.toInt())
    }
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model)
                    .crossfade(true)
                    .apply {
                        transformation?.let { transformations(it) }
                    }
                    .build(),
                placeholder = if (withLoadingIndicator) getGitPainter(
                    Size(
                        Dimension.Pixels(loadingIndicatorSize),
                        Dimension.Pixels(loadingIndicatorSize)
                    )
                ) else null,
                error = painterResource(id = R.drawable.no_data_found),
                contentScale = ContentScale.Inside,
                contentDescription = contentDescription ?: "Image: $model",
                modifier = modifier
                    .sharedBounds(
                        rememberSharedContentState(key = model),
                        animatedVisibilityScope = this
                    )
            )
        }
    }
}