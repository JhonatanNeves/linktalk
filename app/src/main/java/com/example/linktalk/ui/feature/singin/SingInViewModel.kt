package com.example.linktalk.ui.feature.singin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(SingInFormState())
    private set

    fun onFormEvent(event: SingInFormEvent) {
        when (event) {
            is SingInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }
            is SingInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }
            SingInFormEvent.Submit -> {
                doSingIn()
            }
        }
    }
    private fun doSingIn() {
        formState = formState.copy(isLoading = true)
    }
}