package com.jeziellago.hellokoin.features.hello.di

import com.jeziellago.hellokoin.core.api.ApiService
import com.jeziellago.hellokoin.features.hello.data.api.HelloService
import com.jeziellago.hellokoin.features.hello.data.repository.HelloRepositoryImpl
import com.jeziellago.hellokoin.features.hello.domain.repositories.HelloRepository
import com.jeziellago.hellokoin.features.hello.domain.usecase.HelloUseCase
import com.jeziellago.hellokoin.features.hello.presentation.HelloViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal val helloModule = module {

    factory {
        ApiService.createService(HelloService::class.java)
    }

    factory<HelloRepository> {
        HelloRepositoryImpl(helloService = get())
    }

    factory {
        HelloUseCase(helloRepository = get())
    }

    viewModel {
        HelloViewModel(
            helloUseCase = get()
        )
    }
}

internal val helloDependencies by lazy {
    loadKoinModules(helloModule)
}

fun initHelloDependencies() = helloDependencies