package com.example.linktalk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.linktalk.navigation.ChatNavHost
import com.example.linktalk.navigation.rememberLinkTalkNavigationState
import com.example.linktalk.ui.components.BottomNavigationMenu
import com.example.linktalk.ui.theme.Grey1

@Composable
fun ChatApp() {

    val navigationState = rememberLinkTalkNavigationState()

    Scaffold(
        bottomBar = {
            val topLevelDestinations = navigationState.topLevelDestination.toTypedArray()
            if (topLevelDestinations.contains(navigationState.currentTopLevelDestination)){
                BottomNavigationMenu(navigationState = navigationState)
            }
        },
        containerColor = Grey1,
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(innerPaddings)
                .padding(innerPaddings)
                .imePadding()
                .fillMaxSize()
        ) {
            ChatNavHost(
                navigationState = navigationState,
            )
        }
    }
}