package com.uteke.contactbook.network.retrofit

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientFactory(
    private val moshi: Moshi,
) {
    fun create(url: String): Retrofit =
        Retrofit.Builder()
            .client(httpClient())
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    private fun httpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .connectTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .build()

    private fun loggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    private companion object {
        const val DEFAULT_TIMEOUT_IN_SEC = 30L
    }
}