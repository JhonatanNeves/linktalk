package com.example.linktalk.ui.di

import com.example.linktalk.ui.feature.signup.SignUpFormState
import com.example.linktalk.ui.feature.signup.SignUpFormValidator
import com.example.linktalk.ui.validator.FormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface FormValidatorModule {
    @Binds
    fun bindSignUpFormValidator(
        signUpFormValidator: SignUpFormValidator
    ): FormValidator<SignUpFormState>
}