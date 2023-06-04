package com.reihan.finaljetmoviesapp.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorScheme = darkColors(
    primary = LightBlue200,
    primaryVariant = LightBlue700 ,
    secondary = DarkBlue500
)

private val LightColorScheme = lightColors(
    primary = LightBlue500,
    primaryVariant = LightBlue700 ,
    secondary = DarkBlue500

)

@Composable
fun JetMovieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}