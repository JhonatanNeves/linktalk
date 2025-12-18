package com.example.linktalk.ui.feature.chats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.linktalk.R
import com.example.linktalk.model.Chat
import com.example.linktalk.model.User
import com.example.linktalk.model.fake.chat1
import com.example.linktalk.model.fake.chat2
import com.example.linktalk.model.fake.chat3
import com.example.linktalk.model.fake.user1
import com.example.linktalk.model.fake.user2
import com.example.linktalk.model.fake.user3
import com.example.linktalk.model.fake.user4
import com.example.linktalk.ui.components.AnimatedContent
import com.example.linktalk.ui.components.ChatItem
import com.example.linktalk.ui.components.GeneralError
import com.example.linktalk.ui.components.ChatItemShimmer
import com.example.linktalk.ui.components.ChatScaffold
import com.example.linktalk.ui.components.ChatTopAppBar
import com.example.linktalk.ui.components.GeneralEmptyList
import com.example.linktalk.ui.components.PrimaryButton
import com.example.linktalk.ui.theme.Grey1
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun ChatsRoute(
    viewModel: ChatsViewModel = hiltViewModel(),
    navigateToChatDetail: (Chat) -> Unit
) {
    val user by viewModel.currentUserFlow.collectAsStateWithLifecycle()
    val chatsListUiState by viewModel.chatsListUiState.collectAsStateWithLifecycle()

    ChatsScreen(
        user = user,
        chatsListUiState = chatsListUiState,
        onTryAgainClick = {
            viewModel.getChats(isRefresh = true)
        },
        onChatClick = navigateToChatDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    user: User?,
    chatsListUiState: ChatsViewModel.ChatsListUiState,
    onTryAgainClick: () -> Unit,
    onChatClick: (Chat) -> Unit,
) {
    ChatScaffold(
        topBar = {
            ChatTopAppBar(
                title = {
                    Text(
                        text = AnnotatedString.fromHtml(
                            stringResource(
                                R.string.feature_chats_greeting,
                                user?.firstName ?: ""
                            )
                        ),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                expandedHeight = 100.dp
            )
        },
    ) {
        when (chatsListUiState) {
            ChatsViewModel.ChatsListUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    repeat(7) { index ->
                        ChatItemShimmer()

                        if (index < 6) {
                            HorizontalDivider(
                                color = Grey1
                            )
                        }
                    }
                }
            }

            is ChatsViewModel.ChatsListUiState.Success -> {
                when (chatsListUiState.chats.isNotEmpty()) {
                    true -> {
                        ChatsListContent(
                            chats = chatsListUiState.chats,
                            onChatClick = onChatClick,
                        )
                    }

                    false -> {
                        GeneralEmptyList(
                            message = stringResource(R.string.feature_chats_empty_list),
                            resource = {
                                AnimatedContent(
                                    resId = R.raw.animation_empty_list
                                )
                            }

                        )
                    }

                }
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
                            onClick = onTryAgainClick,
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium))
                        )
                    }
                )
            }

        }
    }
}

@Composable
fun ChatsListContent(
    chats: List<Chat>,
    onChatClick: (Chat) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(chats) { index, chat ->
            ChatItem(
                chat = chat,
                onClick = {
                    onChatClick(chat)
                }
            )

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
            user = user1,
            chatsListUiState = ChatsViewModel.ChatsListUiState.Loading,
            onTryAgainClick = {},
            onChatClick = {},
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "fr")
@Composable
private fun ChatsScreenSuccessPreview() {
    LinkTalkTheme {
        ChatsScreen(
            user = user3,
            chatsListUiState = ChatsViewModel.ChatsListUiState.Success(
                chats = listOf(
                    chat1,
                    chat2,
                    chat3
                ),
            ),
            onTryAgainClick = {},
            onChatClick = {},
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "fr")
@Composable
private fun ChatsScreenSuccessEmptyPreview() {
    LinkTalkTheme {
        ChatsScreen(
            user4,
            chatsListUiState = ChatsViewModel.ChatsListUiState.Success(
                chats = emptyList(),
            ),
            onTryAgainClick = {},
            onChatClick = {}
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "fr")
@Composable
private fun ChatsScreenErrorPreview() {
    LinkTalkTheme {
        ChatsScreen(
            user2,
            chatsListUiState = ChatsViewModel.ChatsListUiState.Error,
            onTryAgainClick = {},
            onChatClick = {}
        )
    }
}