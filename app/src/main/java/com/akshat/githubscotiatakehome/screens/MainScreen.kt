package com.akshat.githubscotiatakehome.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.userrepos.UserReposDataItem
import com.akshat.githubscotiatakehome.model.users.UsersData
import com.akshat.githubscotiatakehome.utils.formatWords
import com.akshat.githubscotiatakehome.widgets.GitHubScotiaAppBar
import com.akshat.githubscotiatakehome.widgets.GithubStateImage
import com.akshat.githubscotiatakehome.widgets.SearchButton
import com.akshat.githubscotiatakehome.widgets.UserInputText

@Composable
fun MainScreen(
    navController: NavController, mainViewModel: MainViewModel = hiltViewModel()
) {

    var userId by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        GitHubScotiaAppBar(
            title = "GitHub Scotia Take Home Assignment !",
            navController = navController,
            elevation = 7.dp
        )
    }) {
        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            Column(
                modifier = Modifier.padding(top = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 2.dp, top = 2.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    UserInputText(modifier = Modifier.padding(
                        start = 10.dp,
                        top = 9.dp,
                    ), text = userId, label = "Enter a Github user id", onTextChange = {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }) userId = it
                    })

                    SearchButton(modifier = Modifier.padding(
                        top = 9.dp, end = 9.dp
                    ), text = "Search", onClick = {
                        if (userId.isNotEmpty()) {
                            //PopulateData(userId = "daynamic", mainViewModel = mainViewModel)
                            userId = ""
                        }

                    })

                }
                PopulateData(userId = "daynamic", mainViewModel = mainViewModel)

            }
        }
    }

}

@Composable
fun PopulateData(userId: String, mainViewModel: MainViewModel) {
    val userData = produceState<DataOrException<UsersData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getUsersData(userName = userId)
    }.value

    if (userData.loading == true) {
        CircularProgressIndicator()
    } else if (userData.data != null) {
        //  Text(text = userData.data.toString())
        val userReposData =
            produceState<DataOrException<List<UserReposDataItem>, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = mainViewModel.getUserReposData(userName = "daynamic")
            }.value
        if (userReposData.loading == true) {
            CircularProgressIndicator()
        } else if (userReposData.data != null) {
            NameImageContent(
                data = userData, reposData = userReposData, mainViewModel = mainViewModel
            )
        }


    }
}

@Composable
fun NameImageContent(
    data: DataOrException<UsersData, Boolean, Exception>,
    mainViewModel: MainViewModel,
    reposData: DataOrException<List<UserReposDataItem>, Boolean, Exception>
) {

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
                data.data?.let {
                    GithubStateImage(imageUrl = it.avatar_url)
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = formatWords(it.name),
                        color = Color.Gray,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }

        LazyColumn {
            reposData.data?.let {
                items(items = it) { reposData ->
                    //   Text(text = reposData.name)
                    ReposDataRow(repoData = reposData)
                }
            }
        }


    }

}

@Composable
fun ReposDataRow(repoData: UserReposDataItem) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {},
        shape = RectangleShape,
        colors = CardColors(
            Color.White, Color.Black, Color.White, Color.White
        ),
        elevation = CardDefaults.cardElevation(6.dp)

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











