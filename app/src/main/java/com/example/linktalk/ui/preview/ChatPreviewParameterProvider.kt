package com.example.linktalk.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.linktalk.model.Chat
import com.example.linktalk.model.fake.chat1
import com.example.linktalk.model.fake.chat2
import com.example.linktalk.model.fake.chat3

class ChatPreviewParameterProvider : PreviewParameterProvider<Chat> {
    override val values: Sequence<Chat> = sequenceOf(
        chat1,
        chat2,
        chat3
    )
}