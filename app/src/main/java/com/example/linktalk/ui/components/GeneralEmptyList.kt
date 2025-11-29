package com.example.linktalk.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun GeneralEmptyList(
    message: String,
    modifier: Modifier = Modifier,
    resource: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        resource?.let {
            Box(
                modifier = Modifier
                    .sizeIn(
                        maxWidth = 400.dp,
                        maxHeight = 400.dp,
                    )
            ) {
                it()
            }
            Spacer(Modifier.height(32.dp))
        }

        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun GeneralEmptyListPreview() {
    LinkTalkTheme {
        GeneralEmptyList(
            message = "Empty List!",
        )
    }
}