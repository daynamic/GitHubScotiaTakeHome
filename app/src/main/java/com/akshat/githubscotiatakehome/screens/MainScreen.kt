package com.akshat.githubscotiatakehome.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.userrepos.UserReposData
import com.akshat.githubscotiatakehome.model.users.UsersData

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
){

   /*val userData = produceState<DataOrException<UsersData, Boolean, Exception>>(
       initialValue = DataOrException(loading = true)) {
       value = mainViewModel.getUsersData(userName = "daynamic")
   }.value*/

    val userReposData = produceState<DataOrException<UserReposData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getUserReposData(userName = "daynamic")
    }.value

    if (userReposData.loading == true){
        CircularProgressIndicator()
    } else if (userReposData.data != null){
        Text(text = userReposData.data.toString())
    }

}