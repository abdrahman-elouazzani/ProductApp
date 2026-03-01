package com.elouazzani.core.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    // 2. Provide Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl("https://sephoraandroid.github.io/") // Replace with your base URL
            .client(get()) // Automatically injects the OkHttpClient defined above
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}