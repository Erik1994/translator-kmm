package com.example.kmptranslator.core.domain.util

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class CommonStateFlow <T> actual constructor(
    private val stateFlow: StateFlow<T>
): CommonFlow<T>(stateFlow), StateFlow<T> {
    override val replayCache: List<T>
        get() = stateFlow.replayCache

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        stateFlow.collect(collector)
    }

    override val value: T
        get() = stateFlow.value
}
