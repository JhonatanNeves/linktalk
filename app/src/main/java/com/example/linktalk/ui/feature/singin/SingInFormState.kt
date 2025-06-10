package com.example.linktalk.ui.feature.singin

data class SingInFormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
)
