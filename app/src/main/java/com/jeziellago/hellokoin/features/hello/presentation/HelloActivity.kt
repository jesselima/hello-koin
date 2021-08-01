package com.jeziellago.hellokoin.features.hello.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.jeziellago.hellokoin.R
import kotlinx.android.synthetic.main.activity_hello.*
import org.koin.android.viewmodel.ext.android.viewModel

class HelloActivity : AppCompatActivity() {

    private val helloViewModel: HelloViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        setupObservers()
        setupListeners()
      }

    private fun setupListeners() {
        buttonGetMessage.setOnClickListener {
            helloViewModel.getHelloMessage()
        }
    }

    private fun setupObservers() {
        helloViewModel.state.observe(this, { state ->
            state?.let {
                renderState(it)
            }
        })
    }

    private fun renderState(state: HelloState) {
        textViewMessage.text = state.hello.message
        loadingHelloIndicator.isVisible = state.isLoading
        textErrorView.isVisible = state.errorMessage.isNotEmpty()
        textViewMessage.isVisible = state.errorMessage.isEmpty()
    }

    private fun showError(message: String? = null) {
        val errorText = if(message.isNullOrEmpty()) {
            getString(R.string.message_generic_error)
        } else {
            message
        }
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
    }
}
