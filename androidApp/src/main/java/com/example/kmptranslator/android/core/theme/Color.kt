package com.example.kmptranslator.android.core.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.example.kmptranslator.core.presentation.Colors

val AccentViolet = Color(Colors.AccentViolet)
val LightBlue = Color(Colors.LightBlue)
val LightBlueGray = Color(Colors.LightBlueGrey)
val TextBlack = Color(Colors.TextBlack)
val DarkGray = Color(Colors.DarkGrey)


val lightColors = lightColorScheme(
    primary = AccentViolet,
    background = LightBlueGray,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = darkColorScheme(
    primary = AccentViolet,
    background = DarkGray,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGray,
    onSurface = Color.White
)