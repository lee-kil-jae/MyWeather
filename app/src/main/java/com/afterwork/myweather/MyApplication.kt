package com.afterwork.myweather

import android.app.Application
import com.afterwork.myweather.di.myDiModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(applicationContext)
        startKoin {
            androidContext(applicationContext)
            modules(myDiModule)
        }
    }
}