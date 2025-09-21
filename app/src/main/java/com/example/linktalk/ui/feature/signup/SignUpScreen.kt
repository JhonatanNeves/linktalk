package com.example.linktalk.ui.feature.signup

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.linktalk.R
import com.example.linktalk.ui.components.PrimaryButton
import com.example.linktalk.ui.components.ProfilePictureOptionsModalBottomSheet
import com.example.linktalk.ui.components.ProfilePictureSelector
import com.example.linktalk.ui.components.SecondaryTextField
import com.example.linktalk.ui.theme.BackgroundGradient
import com.example.linktalk.ui.theme.LinkTalkTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    viewModel: SingUpViewModel = viewModel()
) {
    val formState = viewModel.formState
    SignUpScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    formState: SingUpFormState,
    onFormEvent: (SingUpFormEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(56.dp))

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ProfilePictureSelector(
                        imageUri = formState.profilePictureUri,
                        modifier = Modifier
                            .clickable {
                                onFormEvent(SingUpFormEvent.OpenProfilePictureOptionsModalBottomSheet)
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_first_name),
                        value = formState.firstName,
                        onValueChange = {SingUpFormEvent.FirstNameChanged(it)}
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_last_name),
                        value = formState.lastName,
                        onValueChange = {onFormEvent(SingUpFormEvent.LastNameChanged(it))}
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_email),
                        value = formState.email,
                        onValueChange = {onFormEvent(SingUpFormEvent.EmailChanged(it))},
                        keyboardType = KeyboardType.Email
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password),
                        value = formState.password,
                        onValueChange = {onFormEvent(SingUpFormEvent.PasswordChanged(it))},
                        keyboardType = KeyboardType.Password
                    )
                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = stringResource(id = R.string.feature_sign_up_password_confirmation),
                        value = formState.passwordConfirmation,
                        onValueChange = {onFormEvent(SingUpFormEvent.PasswordConfirmationChanged(it))},
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                    Spacer(modifier = Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.feature_sign_up_button),
                        onClick = {onFormEvent(SingUpFormEvent.Submit)}
                    )
                }
            }

            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            if (formState.isProfilePictureModalBottomSheetOpen){
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = {uri ->
                        onFormEvent(SingUpFormEvent.ProfilePhotoUriChanged(uri))
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onFormEvent(SingUpFormEvent.CloseProfilePictureOptionsModalBottomSheet)
                            }
                        }
                    },
                    onDismissRequest = { onFormEvent(SingUpFormEvent.CloseProfilePictureOptionsModalBottomSheet) },
                    sheetState = sheetState,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    LinkTalkTheme {
        SignUpScreen(
            formState = SingUpFormState(),
            onFormEvent = {}
        )
    }
}