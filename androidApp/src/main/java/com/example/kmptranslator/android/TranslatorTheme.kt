package com.example.kmptranslator.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.example.kmptranslator.android.core.theme.AppTypography
import com.example.kmptranslator.android.core.theme.Dimensions
import com.example.kmptranslator.android.core.theme.LocalDimension
import com.example.kmptranslator.android.core.theme.Shapes
import com.example.kmptranslator.android.core.theme.darkColors
import com.example.kmptranslator.android.core.theme.lightColors

@Composable
fun TranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }

    CompositionLocalProvider(LocalDimension provides Dimensions()) {
        MaterialTheme(
            colorScheme = colors,
            typography = AppTypography,
            shapes = Shapes,
            content = content
        )
    }
}
