package com.jeziellago.hellokoin.core

import android.app.Application
import com.jeziellago.hellokoin.core.api.connectionchecker.ConnectionCheckerModule.loadConnectionCheckerModule
import com.jeziellago.hellokoin.features.hello.di.initHelloDependencies
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// TODO 2 - Criar uma classe que estende a classe aplication.
//  No onCreate iimplementar as funções startKoin {}, e dentro dela, o androidLogger() (que vai
//  disparar logs Level.INFO no LogCat do Android Studio) e androidContext() que vai disponibilizar
//  o contexto de aplicação do Android através do Koin.
//  Não necessariamente a inicialização deve estar aqui. Existem outras formas. Aqui é uma delas.
class HelloApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HelloApplication)
        }
        initHelloDependencies()
        setupPicassoInstance()
        loadConnectionCheckerModule()
    }

    private fun setupPicassoInstance() {
        val picassoBuilder = Picasso.Builder(this)
        picassoBuilder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        val picasso = picassoBuilder.build()
        picasso.setIndicatorsEnabled(true)
        picasso.isLoggingEnabled = true
        Picasso.setSingletonInstance(picasso)
    }

}