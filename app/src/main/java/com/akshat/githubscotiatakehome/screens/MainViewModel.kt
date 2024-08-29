package com.akshat.githubscotiatakehome.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.userrepos.UserReposData
import com.akshat.githubscotiatakehome.model.users.UsersData
import com.akshat.githubscotiatakehome.repository.GitHubScotiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: GitHubScotiaRepository) : ViewModel(){
    val userData : MutableState<DataOrException<UsersData, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    val userReposData : MutableState<DataOrException<UserReposData, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    suspend fun getUsersData(userName: String) :
            DataOrException<UsersData, Boolean, Exception>{
        return repository.getUsersData(userName = userName)
    }

    suspend fun getUserReposData(userName: String) :
            DataOrException<UserReposData, Boolean, Exception>{
        return repository.getUserReposData(userName = userName)
    }

}