package com.example.composetraining.core.ui.base.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BasicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorsPalette
    } else {
        LightColorsPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

private val DarkColorsPalette = darkColors(
    surface = Color.Black,
    onSurface = Color.DarkGray,
    primary = Navy,
    onPrimary = Chartreuse,
)

private val LightColorsPalette = lightColors(
    surface = Yellow,
    onSurface = Color.Black,
    primary = LightBlue,
    onPrimary = Navy
)
