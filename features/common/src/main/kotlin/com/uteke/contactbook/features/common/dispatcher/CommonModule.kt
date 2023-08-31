package com.uteke.contactbook.features.common.dispatcher

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::StandardDispatcherProvider) { bind<DispatcherProvider>() }
}