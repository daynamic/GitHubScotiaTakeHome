package com.akshat.githubscotiatakehome.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.UsersData

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
){

   val userData = produceState<DataOrException<UsersData, Boolean, Exception>>(
       initialValue = DataOrException(loading = true)) {
       value = mainViewModel.getUsersData(userName = "daynamic")
   }.value

    if (userData.loading == true){
        CircularProgressIndicator()
    } else if (userData.data != null){
        Text(text = userData.data.toString())
    }

}