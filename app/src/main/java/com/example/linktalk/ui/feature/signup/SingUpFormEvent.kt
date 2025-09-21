package com.example.linktalk.ui.feature.signup

import android.net.Uri

sealed interface SingUpFormEvent {
    data class ProfilePhotoUriChanged(val uri: Uri?) : SingUpFormEvent
    data class FirstNameChanged(val firstName: String) : SingUpFormEvent
    data class LastNameChanged(val lastName: String) : SingUpFormEvent
    data class EmailChanged(val email: String) : SingUpFormEvent
    data class PasswordChanged(val password: String) : SingUpFormEvent
    data class PasswordConfirmationChanged(val passwordConfirmation: String) : SingUpFormEvent
    data object OpenProfilePictureOptionsModalBottomSheet: SingUpFormEvent
    data object CloseProfilePictureOptionsModalBottomSheet: SingUpFormEvent
    data object Submit: SingUpFormEvent
}