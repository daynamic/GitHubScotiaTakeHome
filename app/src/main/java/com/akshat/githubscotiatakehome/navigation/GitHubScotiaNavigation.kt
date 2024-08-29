package com.akshat.githubscotiatakehome.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.akshat.githubscotiatakehome.navigation.GithubScotiaScreens.MainScreen
import com.akshat.githubscotiatakehome.screens.MainScreen
import com.akshat.githubscotiatakehome.screens.MainViewModel

@Composable
fun GitHubScotiaNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreen.name
    ) {
        composable(MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

    }

}