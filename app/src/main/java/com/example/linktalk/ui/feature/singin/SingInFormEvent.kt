package com.example.linktalk.ui.feature.singin

sealed interface SingInFormEvent {
    data class EmailChanged(val email: String) : SingInFormEvent
    data class PasswordChanged(val password: String) : SingInFormEvent
    data object Submit : SingInFormEvent

}