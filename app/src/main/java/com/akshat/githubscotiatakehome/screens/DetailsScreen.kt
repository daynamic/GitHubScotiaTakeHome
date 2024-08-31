package com.akshat.githubscotiatakehome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.akshat.githubscotiatakehome.model.userrepos.UserReposDataItem
import com.akshat.githubscotiatakehome.utils.formatDate
import com.akshat.githubscotiatakehome.widgets.GitHubScotiaAppBar
import com.akshat.githubscotiatakehome.widgets.HeaderViewContent

@Composable
fun DetailsScreen(
    navController: NavController, selectedRepos: UserReposDataItem
) {
    DetailsScaffold(
        selectedRepo = selectedRepos, navController = navController
    )
}

@Composable
fun DetailsScaffold(selectedRepo: UserReposDataItem, navController: NavController) {
    Scaffold(topBar = {
        GitHubScotiaAppBar(
            title = selectedRepo.name,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController,
            elevation = 7.dp
        )
    }) { it ->
        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            Column(
                Modifier
                    .padding(
                        start = 4.dp, top = 9.dp, end = 4.dp, bottom = 4.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailsContent(data = selectedRepo, navController = navController)
            }

        }
    }
}

@Composable
fun DetailsContent(data: UserReposDataItem, navController: NavController) {
    HeaderViewContent(data.owner.avatar_url, data.owner.login)

    Column(
        Modifier
            .padding(
                start = 4.dp, top = 9.dp, end = 4.dp, bottom = 4.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = data.name,
            modifier = Modifier.padding(start = 12.dp, top = 1.dp, end = 12.dp, bottom = 1.dp),
            color = Color.Gray,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold,
        )

        Text(
            text = if (data.description.isNullOrBlank()) "Description Not Available" else data.description,
            modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 1.dp),
            color = Color.Gray,
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append("Language used : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                    append(data.language)
                }
            },
            modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 1.dp),
            color = Color.Gray,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append("Created On : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                    append(formatDate(data.created_at))
                }
            },
            modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 1.dp),
            color = Color.Gray,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append("Default Branch : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                    append(data.default_branch)
                }
            },
            modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 1.dp),
            color = Color.Gray,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append("Forks : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                    append(if (data.forks > 5000) data.forks.toString() + "⭐" else data.forks.toString())
                }
            },
            modifier = Modifier.padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 1.dp),
            color = if (data.forks > 5000) Color.Red else Color.Gray,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge,
        )
    }


}
