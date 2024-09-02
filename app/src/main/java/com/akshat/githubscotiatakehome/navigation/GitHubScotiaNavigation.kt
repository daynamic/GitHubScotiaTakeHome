package com.akshat.githubscotiatakehome.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akshat.githubscotiatakehome.model.userrepos.UserReposDataItem
import com.akshat.githubscotiatakehome.navigation.GithubScotiaScreens.MainScreen
import com.akshat.githubscotiatakehome.screens.main.MainScreen
import com.akshat.githubscotiatakehome.screens.details.DetailsScreen
import com.akshat.githubscotiatakehome.screens.MainViewModel
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun GitHubScotiaNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = MainScreen.name
    ) {
        composable(MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                navController = navController, mainViewModel = mainViewModel
            )
        }

        composable(
            GithubScotiaScreens.DetailScreen.name + "/{selectedRepos}",
            arguments = listOf(navArgument(name = "selectedRepos") {
                type = NavType.StringType
            })
        ) { _ ->
            val selected =
                navController.currentBackStackEntry?.arguments?.getString("selectedRepos")
            if (!selected.isNullOrBlank()) {
                val selectedCarListingDecoded = URLDecoder.decode(selected, "utf-8")
                val selectedRepos =
                    Gson().fromJson(selectedCarListingDecoded, UserReposDataItem::class.java)
                DetailsScreen(
                    navController = navController, selectedRepos = selectedRepos
                )
            }
        }

    }

}