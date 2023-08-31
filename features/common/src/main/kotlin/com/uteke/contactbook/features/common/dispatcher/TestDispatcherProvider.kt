package com.uteke.contactbook.features.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler

class TestDispatcherProvider(
    private val scheduler: TestCoroutineScheduler,
) : DispatcherProvider {
    override val ui: CoroutineDispatcher get() = StandardTestDispatcher(scheduler)

    override val io: CoroutineDispatcher get() = StandardTestDispatcher(scheduler)

    override val default: CoroutineDispatcher get() = StandardTestDispatcher(scheduler)
}