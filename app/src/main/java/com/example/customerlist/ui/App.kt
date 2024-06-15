package com.example.customerlist.ui

import android.app.Application
import com.example.customerlist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //init the coint
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}