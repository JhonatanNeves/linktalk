package com.example.linktalk.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .height(64.dp),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        )
    ) {
        Box(
            modifier = Modifier
                .animateContentSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp)
                        .aspectRatio(1f),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp,
                    strokeCap = StrokeCap.Round,
                )
            } else {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    LinkTalkTheme {
        Column {
            PrimaryButton(
                text = "Sing In",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryButtonLoadingPreview() {
    LinkTalkTheme {
        Column {
            PrimaryButton(
                text = "Sing In",
                onClick = {},
                isLoading = true,
            )
        }
    }
}