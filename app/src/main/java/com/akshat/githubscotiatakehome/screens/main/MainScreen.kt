package com.akshat.githubscotiatakehome.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.userrepos.UserReposDataItem
import com.akshat.githubscotiatakehome.model.users.UsersData
import com.akshat.githubscotiatakehome.navigation.GithubScotiaScreens
import com.akshat.githubscotiatakehome.screens.MainViewModel
import com.akshat.githubscotiatakehome.widgets.GitHubScotiaAppBar
import com.akshat.githubscotiatakehome.widgets.HeaderViewContent
import com.akshat.githubscotiatakehome.widgets.ReposDataRow
import com.akshat.githubscotiatakehome.widgets.SearchButton
import com.akshat.githubscotiatakehome.widgets.UserInputText
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun MainScreen(
    navController: NavController, mainViewModel: MainViewModel = hiltViewModel()
) {

    var userId by remember {
        mutableStateOf("")
    }

    var buttonState by remember {
        mutableStateOf(false)
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
                        buttonState = true
                    })
                }
                if (buttonState) {
                    PopulateData(
                        userId = userId,
                        mainViewModel = mainViewModel,
                        navController = navController
                    )
                }


            }

        }
    }

}


@Composable
fun PopulateData(userId: String, mainViewModel: MainViewModel, navController: NavController) {
    val userData = produceState<DataOrException<UsersData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getUsersData(userName = userId)
    }.value

    if (userData.loading == true) {
        CircularProgressIndicator()
    } else if (userData.data != null) {
        val userReposData =
            produceState<DataOrException<List<UserReposDataItem>, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = mainViewModel.getUserReposData(userName = userId)
            }.value
        if (userReposData.loading == true) {
            CircularProgressIndicator()
        } else if (userReposData.data != null) {
            NameImageContent(
                data = userData, reposData = userReposData, navController = navController
            )
        }
    } else {
        Text(
            text = "$userId is not valid",
            color = Color.Gray,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun NameImageContent(
    data: DataOrException<UsersData, Boolean, Exception>,
    reposData: DataOrException<List<UserReposDataItem>, Boolean, Exception>,
    navController: NavController
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
        data.data?.let {
            HeaderViewContent(
                userImageUrl = it.avatar_url,
                userFullName = if (!it.name.isNullOrBlank()) it.name else ""
            )
        }
        LazyColumn {
            reposData.data?.let {
                items(items = it) { reposData ->
                    ReposDataRow(repoData = reposData) { selectedRepo ->
                        val selected = Gson().toJson(selectedRepo)
                        val selectedRepos = URLEncoder.encode(selected, "utf-8")
                        navController.navigate(route = GithubScotiaScreens.DetailScreen.name + "/$selectedRepos")

                    }
                }
            }
        }


    }

}














