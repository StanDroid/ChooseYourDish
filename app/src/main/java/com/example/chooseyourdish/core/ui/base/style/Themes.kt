package com.example.chooseyourdish.core.ui.base.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BasicTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (isDarkTheme) {
        DarkColorsPalette
    } else {
        LightColorsPalette
    }

    MaterialTheme(
        colors = colors,
        typography = MealsTypography,
        shapes = MealsShapes,
        content = content
    )
    systemUiController.setSystemBarsColor(color = colors.primaryVariant)
}

private val DarkColorsPalette = darkColors(
    surface = Color.Black,
    onSurface = Color.DarkGray,
    primary = Red300,
    onPrimary = Color.Black,
    primaryVariant = Red700,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)

private val LightColorsPalette = lightColors(
    surface = Yellow,
    onSurface = Color.Black,
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

private val MealsShapes = Shapes(
    small = CutCornerShape(topStart = 8.dp),
    medium = CutCornerShape(topStart = 24.dp),
    large = RoundedCornerShape(8.dp)
)