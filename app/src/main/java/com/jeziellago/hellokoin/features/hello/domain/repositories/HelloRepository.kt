package com.jeziellago.hellokoin.features.hello.domain.repositories

import com.jeziellago.hellokoin.features.hello.data.models.HelloResponse

interface HelloRepository {
    suspend fun fetchHelloMessage(): HelloResponse?
}