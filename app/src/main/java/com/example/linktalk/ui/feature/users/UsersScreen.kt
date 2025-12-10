package com.example.linktalk.ui.feature.users

import com.example.linktalk.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.linktalk.model.User
import com.example.linktalk.model.fake.user2
import com.example.linktalk.model.fake.user3
import com.example.linktalk.model.fake.user4
import com.example.linktalk.ui.components.AnimatedContent
import com.example.linktalk.ui.components.ChatScaffold
import com.example.linktalk.ui.components.ChatTopAppBar
import com.example.linktalk.ui.components.GeneralError
import com.example.linktalk.ui.components.PrimaryButton
import com.example.linktalk.ui.components.UserItem
import com.example.linktalk.ui.theme.Grey1
import com.example.linktalk.ui.theme.LinkTalkTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun UsersRoute(
    viewModel: UsersViewModel = hiltViewModel()
) {
    val pagingUsers = viewModel.usersFlow.collectAsLazyPagingItems()
    UsersScreen(
        pagingUsers = pagingUsers
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    pagingUsers: LazyPagingItems<User>
) {
    ChatScaffold(
        topBar = {
            ChatTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.feature_users_title),
                    )
                }
            )
        }
    ) {

        when(pagingUsers.loadState.refresh){
            LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
            is LoadState.NotLoading -> {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(pagingUsers.itemCount) { index ->
                        val user = pagingUsers[index]
                        if (user != null) {
                            UserItem(user = user)

                            if (index < pagingUsers.itemCount - 1) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(top = 16.dp), color = Grey1
                                )
                            }
                        }
                    }
                }
            }
            is LoadState.Error -> {
                GeneralError(
                    title = stringResource(R.string.common_generic_error_title),
                    message = stringResource(R.string.common_generic_error_message),
                    resource = {
                        AnimatedContent()
                    },
                    action = {
                        PrimaryButton(
                            text = stringResource(R.string.common_try_again),
                            onClick = {
                                pagingUsers.refresh()
                            }
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun UsersPreview() {
    LinkTalkTheme {
        val usersFlow = flowOf(
            PagingData.from(
                listOf(
                    user2, user3, user4
                )
            )
        )
        UsersScreen(
            pagingUsers = usersFlow.collectAsLazyPagingItems()
        )
    }
}