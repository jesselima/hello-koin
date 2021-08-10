package com.jeziellago.hellokoin.core.api.connectionchecker

import org.koin.dsl.module

object ConnectionCheckerModule {
    private val connectionCheckerModule = module {
        single<ConnectionChecker> {
            ConnectionCheckerImpl(context = get())
        }
    }
    fun loadConnectionCheckerModule() = connectionCheckerModule
}