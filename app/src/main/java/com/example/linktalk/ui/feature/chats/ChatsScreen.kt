package com.example.linktalk.ui.feature.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.linktalk.R
import com.example.linktalk.model.Chat
import com.example.linktalk.model.fake.chat1
import com.example.linktalk.model.fake.chat2
import com.example.linktalk.model.fake.chat3
import com.example.linktalk.ui.components.AnimatedContent
import com.example.linktalk.ui.components.ChatItem
import com.example.linktalk.ui.components.GeneralError
import com.example.linktalk.ui.components.ChatItemShimmer
import com.example.linktalk.ui.components.PrimaryButton
import com.example.linktalk.ui.theme.Grey1
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun ChatsRoute(
    viewModel: ChatsViewModel = hiltViewModel()
) {
    val chatsListUiState by viewModel.chatsListUiState.collectAsStateWithLifecycle()

    ChatsScreen(
        chatsListUiState = chatsListUiState,
        onTryAgainClick = {
            viewModel.getChats()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ChatsScreen(
    chatsListUiState: ChatsViewModel.ChatsListUiState,
    onTryAgainClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = AnnotatedString.fromHtml(
                            stringResource(
                                R.string.feature_chats_greeting,
                                "Jhonatan"
                            )
                        ),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                expandedHeight = 100.dp
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.extraLarge.copy(
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp),
                    )
                )
                .clip(
                    shape = MaterialTheme.shapes.extraLarge.copy(
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp),
                    )
                )
                .fillMaxSize(),
        ) {
            when (chatsListUiState) {
                ChatsViewModel.ChatsListUiState.Loading -> {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        repeat(7) { index ->
                            ChatItemShimmer()

                            if (index < 6 ) {
                                HorizontalDivider(
                                    color = Grey1
                                )
                            }
                        }
                    }
                }

                is ChatsViewModel.ChatsListUiState.Success -> {
                    ChatsListContent(chatsListUiState.chats)
                }

                ChatsViewModel.ChatsListUiState.Error -> {
                    GeneralError(
                        title = stringResource(R.string.common_generic_error_title),
                        message = stringResource(R.string.common_generic_error_message),
                        resource = {
                            AnimatedContent()
                        },
                        action = {
                            PrimaryButton(
                                text = stringResource(R.string.common_try_again),
                                onClick = onTryAgainClick
                            )
                        }

                    )
                }
            }
        }
    }
}

@Composable
fun ChatsListContent(chats: List<Chat>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(chats) { index, chat ->
            ChatItem(chat)

            if (index < chats.lastIndex) {
                HorizontalDivider(
                    color = Grey1
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "fr")
@Composable
private fun ChatsScreenLoadingPreview() {
    LinkTalkTheme {
        ChatsScreen(
            chatsListUiState = ChatsViewModel.ChatsListUiState.Loading,
            onTryAgainClick = {}
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "fr")
@Composable
private fun ChatsScreenSuccessPreview() {
    LinkTalkTheme {
        ChatsScreen(
            chatsListUiState = ChatsViewModel.ChatsListUiState.Success(
                chats = listOf(
                    chat1,
                    chat2,
                    chat3
                ),
            ),
            onTryAgainClick = {}
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "fr")
@Composable
private fun ChatsScreenErrorPreview() {
    LinkTalkTheme {
        ChatsScreen(
            chatsListUiState = ChatsViewModel.ChatsListUiState.Error,
            onTryAgainClick = {}
        )
    }
}