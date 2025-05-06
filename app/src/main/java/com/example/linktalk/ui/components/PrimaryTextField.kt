package com.example.linktalk.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linktalk.R
import com.example.linktalk.ui.theme.ColorError
import com.example.linktalk.ui.theme.LinkTalkTheme

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    leadingIcon: Int? = null,
    errorMessage: String? = null,
) {
    Column (
        modifier = modifier
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                Surface(
                    shape = CircleShape,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        leadingIcon?.let {
                            Image(
                                painter = painterResource(id = R.drawable.ic_envelope),
                                contentDescription = null,
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            innerTextField()
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            painter = painterResource(id = R.drawable.ic_bottom_nav_profile),
                            contentDescription = null,
                        )
                    }
                }
            }
        )

        errorMessage?.let {
            Text(
                text = "Password Required",
                modifier = Modifier
                    .padding(start = 16.dp),
                color = ColorError,

                )
        }

    }
}

@Preview
@Composable
private fun PrimaryTextFieldPreview() {
    LinkTalkTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            errorMessage = "Password Required"

        )
    }
}