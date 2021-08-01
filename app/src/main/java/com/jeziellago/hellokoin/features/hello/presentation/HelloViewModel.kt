package com.jeziellago.hellokoin.features.hello.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeziellago.hellokoin.features.hello.domain.usecase.HelloUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HelloViewModel(
    private val helloUseCase: HelloUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HelloState>()
    val state: LiveData<HelloState> = _state

    fun getHelloMessage() {

        _state.value = HelloState(isLoading = true)

        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    helloUseCase.getHelloMessage()
                }
            }.onSuccess {
                _state.value = _state.value?.copy(isLoading = false, hello = it)
            }.onFailure {
                _state.value = _state.value?.copy(
                    isLoading = false,
                    errorMessage = it.localizedMessage ?: ""
                )
            }
        }
    }
}