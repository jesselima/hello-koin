package com.jeziellago.hellokoin.features.hello.domain.usecase

import com.jeziellago.hellokoin.features.hello.data.models.mapToDomain
import com.jeziellago.hellokoin.features.hello.domain.entities.Hello
import com.jeziellago.hellokoin.features.hello.domain.repositories.HelloRepository

class HelloUseCase(
    private val helloRepository: HelloRepository
) {

    suspend fun getHelloMessage(): Hello {
        return helloRepository.fetchHelloMessage()?.mapToDomain() ?: Hello()
    }
}