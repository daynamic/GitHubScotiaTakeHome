package com.akshat.githubscotiatakehome.repository

import android.util.Log
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.userrepos.UserReposData
import com.akshat.githubscotiatakehome.model.users.UsersData
import com.akshat.githubscotiatakehome.network.GitHubScotiaAPIs
import javax.inject.Inject

class GitHubScotiaRepository @Inject constructor(private val api: GitHubScotiaAPIs) {

    suspend fun getUsersData(userName: String): DataOrException<UsersData, Boolean, Exception> {
        val response = try {
            api.getUserData(username = userName)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getUsersData : $response")
        return DataOrException(data = response)
    }

    suspend fun getUserReposData(userName: String) : DataOrException<UserReposData, Boolean, Exception> {
        val response = try {
            api.getUserReposData(username = userName)
        } catch (e: Exception){
            Log.d("GETUSERReposeDATA", "getUserReposData :$e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getUserReposData : $response")
        return DataOrException(data = response)
    }


}
