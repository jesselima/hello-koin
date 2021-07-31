package com.jeziellago.hellokoin.core

import android.app.Application
import com.jeziellago.hellokoin.features.feed.di.initHelloDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HelloApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HelloApplication)
        }
        initHelloDependencies()
    }
}