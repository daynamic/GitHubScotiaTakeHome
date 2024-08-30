package com.akshat.githubscotiatakehome.model.userrepos

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("type")
    val type: String
)