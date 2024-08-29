package com.akshat.githubscotiatakehome.model.userrepos

data class UserReposDataItem(
    val created_at: String,
    val default_branch: String,
    val description: String,
    val fork: Boolean,
    val forks: Int,
    val forks_count: Int,
    val forks_url: String,
    val full_name: String,
    val id: Int,
    val language: String,
    val name: String,
    val owner: Owner,
    val url: String,
)