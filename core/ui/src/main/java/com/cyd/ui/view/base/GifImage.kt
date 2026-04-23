package com.cyd.ui.view.base

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.cyd.ui.R

@Composable
fun GifImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageLoader =
        ImageLoader
            .Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()
    Image(
        modifier = modifier,
        painter =
            rememberAsyncImagePainter(
                ImageRequest
                    .Builder(context)
                    .data(data = R.drawable.gif_pizza)
                    .apply(block = {
                        size(Size.ORIGINAL)
                    })
                    .build(),
                imageLoader = imageLoader,
            ),
        contentDescription = null,
    )
}

@Composable
fun getGitPainter(size: Size = Size.ORIGINAL): Painter {
    val context = LocalContext.current

    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    val imageRequest = remember(context, size) {
        ImageRequest.Builder(context)
            .data(data = R.drawable.gif_pizza)
            .size(size)
            .build()
    }

    return rememberAsyncImagePainter(
        model = imageRequest,
        imageLoader = imageLoader,
    )
}
