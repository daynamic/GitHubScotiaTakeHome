package com.akshat.githubscotiatakehome.network

import com.akshat.githubscotiatakehome.model.userrepos.UserReposDataItem
import com.akshat.githubscotiatakehome.model.users.UsersData
import com.akshat.githubscotiatakehome.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubScotiaAPIs {

    @Headers("Accept: application/vnd.github+json")
    @GET(value = "users/{username}")
    suspend fun getUserData(
        @Path("username") username: String,
        @Query("Authorization:") token: String = Constants.BEARER_TOKEN
    ): UsersData

    @Headers("Accept: application/vnd.github+json")
    @GET(value = "users/{username}/repos?per_page=100")
    suspend fun getUserReposData(
        @Path("username") username: String,
        @Query("Authorization:") token: String = Constants.BEARER_TOKEN

    ): List<UserReposDataItem>


}