package com.cyd.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cyd.R
import kotlin.random.Random

@Composable
fun SplashScreen(onEndAction: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val number by remember { mutableIntStateOf(Random.nextInt(0, 3)) }
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(getAnimationSource(number))
        )
        val logoAnimationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            modifier = Modifier.align(Alignment.Center),
            composition = composition,
            progress = { logoAnimationState.progress }
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            onEndAction.invoke()
        }
    }
}

private fun getAnimationSource(number: Int): Int {
    return when (number) {
        0 -> R.raw.animation0
        1 -> R.raw.animation1
        else -> R.raw.animation2
    }
}