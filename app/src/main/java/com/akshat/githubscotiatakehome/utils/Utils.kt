package com.akshat.githubscotiatakehome.utils


fun formatWords(string: String): String {
    return string.trim().split("\\s+".toRegex()).joinToString(" ") { it.capitalize() }
}