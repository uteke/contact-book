package com.uteke.contactbook.features.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class StandardDispatcherProvider : DispatcherProvider {
    override val ui: CoroutineDispatcher get() = Dispatchers.Main

    override val io: CoroutineDispatcher get() = Dispatchers.IO

    override val default: CoroutineDispatcher get() = Dispatchers.Default
}