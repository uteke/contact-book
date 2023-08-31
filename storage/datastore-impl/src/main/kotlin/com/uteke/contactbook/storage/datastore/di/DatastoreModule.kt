package com.uteke.contactbook.storage.datastore.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.uteke.contactbook.storage.datastore.user.UserProtoStore
import com.uteke.contactbook.storage.datastore.user.UsersSerializer
import com.uteke.contactbook.storage.user.UserStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single<UserStore> {
        UserProtoStore(
            dataStore = DataStoreFactory.create(
                serializer = UsersSerializer,
                produceFile = { androidContext().dataStoreFile("users_proto") },
            )
        )
    }
}
