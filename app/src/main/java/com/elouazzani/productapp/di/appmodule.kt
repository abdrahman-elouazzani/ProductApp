package com.elouazzani.productapp.di

import ProductViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ProductViewModel(get(), get()) }
}