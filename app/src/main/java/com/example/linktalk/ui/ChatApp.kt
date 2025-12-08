package com.example.linktalk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.linktalk.navigation.ChatNavHost
import com.example.linktalk.navigation.LinkTalkNavigationState
import com.example.linktalk.navigation.rememberLinkTalkNavigationState
import com.example.linktalk.ui.components.BottomNavigationMenu
import com.example.linktalk.ui.theme.Grey1

@Composable
fun ChatApp(navigationState : LinkTalkNavigationState = rememberLinkTalkNavigationState()) {

    val topLevelDestination = remember(navigationState.topLevelDestination) {
        navigationState.topLevelDestination.toSet()
    }
    Scaffold(
        bottomBar = {
            if (navigationState.currentTopLevelDestination in topLevelDestination){
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