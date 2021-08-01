package com.jeziellago.hellokoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeziellago.hellokoin.features.hello.di.helloModule
import com.jeziellago.hellokoin.features.hello.presentation.HelloState
import com.jeziellago.hellokoin.features.hello.presentation.HelloViewModel
import com.jeziellago.hellokoin.features.users.presentation.UsersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class HelloViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {
        startKoin {
            modules(helloModule)
        }
    }

    @Test
    fun `When call show hello method, assert that state is changed`() = runBlockingTest {
        val helloViewModel by inject<HelloViewModel>()

        helloViewModel.getHelloMessage()

        assertThat(helloViewModel.state.value, IsInstanceOf(HelloState::class.java))
    }
}