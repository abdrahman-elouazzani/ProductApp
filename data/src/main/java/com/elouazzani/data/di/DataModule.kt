package com.elouazzani.data.di

import com.elouazzani.data.apiservices.ProductApiService
import com.elouazzani.data.repositories.ProductRepositoryImpl
import com.elouazzani.domain.repositories.ProductRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    // 3. Provide the specific API Interface
    single<ProductApiService> {
        get<Retrofit>().create(ProductApiService::class.java)
    }

    single<ProductRepository> { ProductRepositoryImpl(get()) }

}
