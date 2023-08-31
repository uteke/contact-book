package com.uteke.contactbook.network.retrofit.di

import com.squareup.moshi.Moshi
import com.uteke.contactbook.network.retrofit.BuildConfig
import com.uteke.contactbook.network.retrofit.RetrofitClientFactory
import com.uteke.contactbook.network.retrofit.user.UserRetrofitApi
import com.uteke.contactbook.network.user.UserApi
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single<Retrofit> {
        RetrofitClientFactory(
            moshi = Moshi.Builder().build(),
        ).create(url = BuildConfig.BASE_URL)
    }

    factoryOf(::UserRetrofitApi) { bind<UserApi>() }
}