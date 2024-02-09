package com.example.kmptranslator.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class CommonMutableStateFlow <T> (mutableStateFlow: MutableStateFlow<T>): MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow(): CommonMutableStateFlow<T> = CommonMutableStateFlow(this)