package com.elouazzani.domain.di

import com.elouazzani.domain.usecases.GetProductsReviewUseCase
import com.elouazzani.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get()) }
    factory { GetProductsReviewUseCase(get()) }

}