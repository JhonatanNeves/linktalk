package com.example.linktalk.ui.feature.singin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linktalk.R
import com.example.linktalk.ui.components.PrimaryTextField
import com.example.linktalk.ui.theme.BackgroundGradient
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun SingInRoute() {
    SingInScreen()
}

@Composable
fun SingInScreen() {
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        var email by remember {
            mutableStateOf("")
        }

        PrimaryTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
private fun SingInScreenPreview() {
    LinkTalkTheme {
        SingInScreen()
    }
}