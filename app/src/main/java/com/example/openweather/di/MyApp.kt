package com.example.openweather.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApp)
            module { listOf(viewModelModule, repositoryModule) }
        }
    }
}