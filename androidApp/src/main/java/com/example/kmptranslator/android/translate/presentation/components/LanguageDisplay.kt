package com.example.kmptranslator.android.translate.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kmptranslator.android.core.theme.LightBlue
import com.example.kmptranslator.android.core.theme.LocalDimension
import com.example.kmptranslator.core.presentation.UiLanguage

@Composable
fun LanguageDisplay(
    language: UiLanguage,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimension.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallLanguageIcon(language = language)
        Spacer(modifier = Modifier.width(dimensions.small))
        Text(
            text = language.language.langName,
            color = LightBlue
        )
    }
}