package com.elouazzani.productapp.application

import android.app.Application
import com.elouazzani.core.di.coreModule
import com.elouazzani.data.di.dataModule
import com.elouazzani.domain.di.domainModule
import com.elouazzani.productapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                appModule,
                coreModule,
                dataModule,
                domainModule
            )
        }
    }
}