package com.akshat.githubscotiatakehome.model.userrepos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserReposDataItem(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("default_branch") val default_branch: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks") val forks: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("language") val language: String,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner
)