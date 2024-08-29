package com.akshat.githubscotiatakehome.repository

import android.util.Log
import com.akshat.githubscotiatakehome.data.DataOrException
import com.akshat.githubscotiatakehome.model.UsersData
import com.akshat.githubscotiatakehome.network.GitHubScotiaAPIs
import javax.inject.Inject

class GitHubScotiaRepository @Inject constructor(private val api: GitHubScotiaAPIs) {

    suspend fun getUsersData(userName: String): DataOrException<UsersData, Boolean, Exception> {
        val response = try {
            api.getUserData(username = userName)
        } catch (e: Exception) {
            Log.d("GETUSERDATA", "getUsersData :$e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getUsersData : $response")
        return DataOrException(data = response)
    }


}
