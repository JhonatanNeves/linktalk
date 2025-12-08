package com.example.linktalk.ui.feature.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.linktalk.model.User
import com.example.linktalk.model.fake.user2
import com.example.linktalk.model.fake.user3
import com.example.linktalk.model.fake.user4
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

@Composable
fun UsersScreen(
    pagingUsers: LazyPagingItems<User>
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pagingUsers.itemCount) { index ->
                val user = pagingUsers[index]
                if (user != null) {
                    Text(text = user.firstName)
                }
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
                    user2,
                    user3,
                    user4
                )
            )
        )
        UsersScreen(
            pagingUsers = usersFlow.collectAsLazyPagingItems()
        )
    }
}