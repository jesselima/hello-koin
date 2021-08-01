package com.jeziellago.hellokoin.features.hello.data.api

import com.jeziellago.hellokoin.features.hello.data.models.HelloResponse
import retrofit2.http.GET

interface HelloService {
    @GET("hello")
    suspend fun getHelloMessage(): HelloResponse?
}