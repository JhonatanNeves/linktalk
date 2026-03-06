package com.example.linktalk.ui.feature.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.linktalk.SelfUser
import com.example.linktalk.ui.components.ChatScaffold
import com.example.linktalk.ui.components.ChatTopAppBar
import com.example.linktalk.ui.components.ProfileMenuItem
import com.example.linktalk.ui.components.RoundedAvatar
import com.example.linktalk.ui.theme.LinkTalkTheme

data class ProfileMenuItemModel(
    val label: String,
    val icon: ImageVector,
    val textColor: Color? = null,
    val onClick: () -> Unit
)

@Composable
fun ProfileUserRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val user by viewModel.user.collectAsStateWithLifecycle()

    ProfileUserScreen(
        onNavigationIconClicked = navigateBack,
        user = user,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUserScreen(
    user: SelfUser?,
    onNavigationIconClicked: () -> Unit,
) {
    val accountItems = listOf(
        ProfileMenuItemModel("Account", Icons.Default.AccountCircle) {},
        ProfileMenuItemModel("Privacy", Icons.Default.Lock) {},
        ProfileMenuItemModel("Notifications", Icons.Default.Notifications) {}
    )

    val appItems = listOf(
        ProfileMenuItemModel("Settings", Icons.Default.Settings) {},
        ProfileMenuItemModel("Help & Support", Icons.Default.AccountCircle) {},
        ProfileMenuItemModel("Logout", Icons.AutoMirrored.Filled.ExitToApp, textColor = Color.Red) {}
    )

    ChatScaffold(
        topBar = {
            ChatTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onNavigationIconClicked() }
                            .padding(start = 10.dp),
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            item {
                RoundedAvatar(
                    imageUri = user?.profilePictureUrl,
                    contentDescription = null,
                    size = 140.dp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${user?.firstName ?: ""} ${user?.lastName ?: ""}",
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                MenuSection(title = "Personal Information", items = accountItems)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                MenuSection(title = "App Settings", items = appItems)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun MenuSection(title: String, items: List<ProfileMenuItemModel>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF5A857C),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                items.forEachIndexed { index, item ->
                    ProfileMenuItem(
                        label = item.label,
                        icon = item.icon,
                        textColor = item.textColor ?: MaterialTheme.colorScheme.inverseOnSurface,
                        onClick = item.onClick
                    )
                    if (index < items.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 52.dp, end = 16.dp),
                            thickness = 0.5.dp,
                            color = Color.White.copy(alpha = 0.2f)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileUserPreview() {
    LinkTalkTheme {
        ProfileUserScreen(
            onNavigationIconClicked = {},
            user = null,
        )
    }
}
