package com.uteke.contactbook.features.common

import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import com.uteke.contactbook.features.common.dispatcher.StandardDispatcherProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::StandardDispatcherProvider) { bind<DispatcherProvider>() }
}