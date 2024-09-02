package com.akshat.githubscotiatakehome.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.userrepos.UserReposDataItem
import com.akshat.githubscotiatakehome.model.users.UsersData
import com.akshat.githubscotiatakehome.utils.Constants
import com.akshat.githubscotiatakehome.utils.formatWords

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
    val focusedColor = Constants.EDIT_TEXT_SELECTED_COLOR

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
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit = { }, enabled: Boolean = true
) {
    val focusedColor = Constants.APP_BAR_THEME_COLOR
    Button(
        onClick = onClick,
        shape = ButtonDefaults.shape,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonColors(
            containerColor = focusedColor,
            contentColor = Color.White,
            disabledContentColor = Color.White,
            disabledContainerColor = focusedColor
        )
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

@Composable
fun ReposDataRow(
    repoData: UserReposDataItem, onItemClick: (UserReposDataItem) -> Unit = { }
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onItemClick(repoData)
            }, shape = RectangleShape, colors = CardColors(
            Color.White, Color.Black, Color.White, Color.White
        ), elevation = CardDefaults.cardElevation(6.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = repoData.name,
                modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 5.dp),
                color = Color.Gray,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
            )

            Text(
                text = if (repoData.description.isNullOrBlank()) "Description Not Available" else repoData.description,
                modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 12.dp),
                color = Color.Gray,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun HeaderViewContent(userImageUrl: String, userFullName: String) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .size(250.dp),
        shape = RectangleShape,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GithubStateImage(imageUrl = userImageUrl)
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = if (!userFullName.isNullOrBlank()) formatWords(userFullName) else "",
                color = Color.Gray,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

        }

    }
}