package com.example.kmptranslator.android.translate.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.kmptranslator.android.core.theme.LocalDimension

@Composable
fun ProgressButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimension.current
    val height = if (isLoading) {
        20.dp
    } else 30.dp
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick)
            .padding(dimensions.small)
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isLoading
        ) { isLoading ->
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = text.uppercase(),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}