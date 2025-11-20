package com.example.linktalk.ui.feature.splash

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import com.example.linktalk.R
import com.example.linktalk.ui.components.AppDialog
import com.example.linktalk.ui.theme.BackgroundGradient
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateToSingIn: () -> Unit,
    onNavigateToMain: () -> Unit,
    onCloseApp: () -> Unit,
) {
    SplashScreen()

    LifecycleStartEffect(Unit) {
        viewModel.checkSession()
        onStopOrDispose {}
    }

    LaunchedEffect(Unit) {
        viewModel.authenticationState.collect { authenticationState ->
            when (authenticationState) {
                SplashViewModel.AuthenticationState.UserAuthenticated -> {
                    onNavigateToMain()

                }

                SplashViewModel.AuthenticationState.UserNotAuthenticated -> {
                    onNavigateToSingIn()
                }
            }
        }
    }

    val showErrorDialog = viewModel.showErrorDialogState
    if (showErrorDialog) {
        AppDialog(
            onDismissRequest = {},
            onConfirmButtonClick = {
                viewModel.dismissErrorDialog()
                onCloseApp()
            },
            message = stringResource(R.string.error_message_when_opening_app),
            confirmButtonText = stringResource(R.string.error_confirm_button_close_app),
        )
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val window = (context as Activity).window
    val statusBarColor = Color(0xFF165E57)
    SideEffect {
        window.statusBarColor = statusBarColor.toArgb()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            false // para texto claro
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(77.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_safety),
                contentDescription = null
            )


            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = context.getString(R.string.splash_safety_info),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    LinkTalkTheme {
        SplashScreen()
    }
}
