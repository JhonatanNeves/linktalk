package com.example.linktalk.ui.feature.singin

import androidx.annotation.StringRes

data class SingInFormState(
    val email: String = "",
    @StringRes
    val emailError: Int? = null,
    val password: String = "",
    @StringRes
    val passwordError: Int? = null,
    val isLoading: Boolean = false,
)
