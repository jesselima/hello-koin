package com.jeziellago.hellokoin.features.hello.presentation

import com.jeziellago.hellokoin.features.hello.domain.entities.Hello

data class HelloState(
    val hello: Hello = Hello(""),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)