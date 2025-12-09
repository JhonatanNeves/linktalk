package com.example.linktalk.ui.components

import android.R.attr.id
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import coil.compose.AsyncImage
import com.example.linktalk.R
import com.example.linktalk.model.Chat
import com.example.linktalk.model.User
import com.example.linktalk.ui.preview.ChatPreviewParameterProvider
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun ChatItem(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    val receiver = remember ( chat.members ){
        chat.members.first { it.self.not() }
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val (
            avatarRef,
            firstNameRef,
            lastMessageRef,
            lastMessageTimeRef,
            unreadMessageCountRef,
        ) = createRefs()
        RoundedAvatar(
            imageUri = receiver.profilePictureUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
        )

        Text(
            text = receiver.firstName,
            modifier = Modifier
                .constrainAs(firstNameRef) {
                    top.linkTo(avatarRef.top)
                    start.linkTo(avatarRef.end, margin = 16.dp)
                    end.linkTo(lastMessageTimeRef.start, margin = 16.dp)
                    bottom.linkTo(lastMessageRef.top)
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = chat.lastMessage ?: "",
            modifier = Modifier
                .constrainAs(lastMessageRef) {
                    top.linkTo(firstNameRef.bottom)
                    start.linkTo(avatarRef.end, margin = 16.dp)
                    end.linkTo(unreadMessageCountRef.start, margin = 16.dp)
                    bottom.linkTo(avatarRef.bottom)
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = chat.timestamp,
            modifier = Modifier
                .constrainAs(lastMessageTimeRef) {
                    top.linkTo(firstNameRef.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(firstNameRef.bottom)
                    width = Dimension.wrapContent
                },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = chat.unreadCount.toString(),
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 6.dp)
                .constrainAs(unreadMessageCountRef) {
                    top.linkTo(lastMessageTimeRef.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(lastMessageRef.bottom)
                    width = Dimension.wrapContent
                    visibility = if (chat.unreadCount > 0) {
                        Visibility.Visible
                    } else Visibility.Gone
                },
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium,
        )


    }
}

@Preview
@Composable
private fun ChatItemPreview(
    @PreviewParameter(ChatPreviewParameterProvider::class)
    chat: Chat,
) {
    LinkTalkTheme {
        ChatItem(
            chat = chat,
        )
    }
}