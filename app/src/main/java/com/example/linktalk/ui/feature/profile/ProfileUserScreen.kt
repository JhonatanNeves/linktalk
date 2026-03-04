
import android.R.attr.tint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linktalk.ui.components.ChatScaffold
import com.example.linktalk.ui.components.ChatTopAppBar
import com.example.linktalk.ui.components.ProfileMenuItem
import com.example.linktalk.ui.components.RoundedAvatar
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun ProfileUserRoute(
    navigateBack: () -> Unit,
) {
    ProfileUserScreen(
        onNavigationIconClicked = navigateBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUserScreen(
    onNavigationIconClicked: () -> Unit,
) {
    ChatScaffold(
        topBar = {
            ChatTopAppBar(
                title = {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Column (
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                        ){
                            Text(
                                text = "Profile",
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onNavigationIconClicked()
                            }
                            .padding(start = 10.dp),
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp)
                .fillMaxSize()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RoundedAvatar(
                imageUri = null,
                contentDescription = null,
                size = 140.dp,
            )

            Text(
                text = "Nome do Usuário",
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                color = Color(0xFF5A857C),
                shape = MaterialTheme.shapes.large
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 8.dp),
                ) {
                    ProfileMenuItem(
                        label = "Account",
                        icon = Icons.Default.AccountCircle,
                        onClick = { /* ação */ },

                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .padding(start = 50.dp),
                        thickness = 0.7.dp,
                        color = Color.LightGray.copy(alpha = 0.5f)
                    )

                    ProfileMenuItem(
                        label = "Notifications",
                        icon = Icons.Default.Notifications,
                        onClick = { /* ação */ }
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .padding(start = 50.dp),
                        thickness = 0.7.dp,
                        color = Color.LightGray.copy(alpha = 0.5f)
                    )

                    ProfileMenuItem(
                        label = "Privacy",
                        icon = Icons.Default.Lock,
                        onClick = { /* ação */ }
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .padding(start = 50.dp),
                        thickness = 0.7.dp,
                        color = Color.LightGray.copy(alpha = 0.5f)
                    )

                    ProfileMenuItem(
                        label = "Delete my account",
                        icon = Icons.Default.ExitToApp,
                        textColor = Color.Red,
                        onClick = { /* ação */ }
                    )
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
        )
    }
}