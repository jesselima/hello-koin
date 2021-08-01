package com.jeziellago.hellokoin.features.hello.data.repository

import com.jeziellago.hellokoin.features.hello.data.api.HelloService
import com.jeziellago.hellokoin.features.hello.data.models.HelloResponse
import com.jeziellago.hellokoin.features.hello.domain.repositories.HelloRepository

internal class HelloRepositoryImpl(
    val helloService: HelloService
) : HelloRepository {

    override suspend fun fetchHelloMessage(): HelloResponse? {
        return helloService.getHelloMessage()
    }

}