package com.uteke.contactbook

import android.app.Application
import com.uteke.contactbook.features.common.dispatcher.commonModule
import com.uteke.contactbook.features.userdetails.di.userDetailsModule
import com.uteke.contactbook.features.userlist.di.userListModule
import com.uteke.contactbook.network.retrofit.di.networkModule
import com.uteke.contactbook.storage.datastore.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule +
                        storageModule +
                        commonModule +
                        userListModule +
                        userDetailsModule,
            )
        }
    }
}