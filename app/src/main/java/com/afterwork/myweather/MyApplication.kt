package com.afterwork.myweather

import android.app.Application
import com.afterwork.myweather.di.myDiModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(applicationContext)
        startKoin(applicationContext, myDiModule)
    }
}