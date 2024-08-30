package com.akshat.githubscotiatakehome.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import coil.compose.rememberImagePainter

@Composable
fun UserInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = { }
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusedColor = Color(
        red = 237, green = 108, blue = 140
    )

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = focusedColor,
            focusedIndicatorColor = focusedColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        maxLines = maxLines,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )

}


@Composable
fun SearchButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit, enabled: Boolean = true
) {
    Button(
        onClick = { onClick() },
        shape = ButtonDefaults.shape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }

}

@Composable
fun GithubStateImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier.clip(RectangleShape)
    )
}