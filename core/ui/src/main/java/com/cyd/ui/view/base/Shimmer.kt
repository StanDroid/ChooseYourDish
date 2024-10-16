package com.cyd.ui.view.base

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.ui.view.base.style.CydTheme

@Preview(showBackground = true)
@Composable
fun ShimmerButtonPreview() {
    CydTheme {
        Row {
            ShimmerButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            ShimmerButton(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ShimmerButton(
    modifier: Modifier = Modifier,
) {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.primary,
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = -500f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val shimmerBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnim, y = -400f),
        end = Offset(x = translateAnim + 500f, 100f)
    )

    Spacer(
        modifier = modifier
            .padding(top = 4.dp, bottom = 10.dp)
            .background(
                shimmerBrush,
                shape = ButtonDefaults.shape
            )
            .height(40.dp)
    )
}
