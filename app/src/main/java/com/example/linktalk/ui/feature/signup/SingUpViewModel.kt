package com.example.linktalk.ui.feature.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SingUpViewModel : ViewModel() {

    var formState by mutableStateOf(SingUpFormState())
        private set

    fun onFormEvent(event: SingUpFormEvent) {
        when (event) {
            is SingUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }
            is SingUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }
            is SingUpFormEvent.LastNameChanged -> {
                formState = formState.copy(lastName = event.lastName)
            }
            is SingUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }
            is SingUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }
            is SingUpFormEvent.PasswordConfirmationChanged -> {
                formState = formState.copy(passwordConfirmation = event.passwordConfirmation)
            }
            SingUpFormEvent.OpenProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }
            SingUpFormEvent.CloseProfilePictureOptionsModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }
            SingUpFormEvent.Submit -> {

            }
        }
    }

    private fun isValidForm(): Boolean {
        return false
    }

    private fun doSingUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
        }
    }
}