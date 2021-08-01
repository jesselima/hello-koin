package com.jeziellago.hellokoin

import com.jeziellago.hellokoin.features.hello.di.helloModule
import com.jeziellago.hellokoin.features.hello.domain.entities.Hello
import com.jeziellago.hellokoin.features.hello.domain.repositories.HelloRepository
import com.jeziellago.hellokoin.features.hello.domain.usecase.HelloUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class HelloUseCaseTest : AutoCloseKoinTest() {

    private val helloRepository by inject<HelloRepository>()

    @Before
    fun setupTest() {
        startKoin {
            modules(helloModule)
        }
    }

    @Test
    fun `Assert that say hello message return Hello object`() = runBlockingTest {
        val helloUseCase = HelloUseCase(helloRepository)

        val message = Hello("Hello Koin!")

        val hello = helloUseCase.getHelloMessage()

        assertThat(message, IsEqual(hello.message))

    }
}