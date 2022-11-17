package com.example.tmdb_compose.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoute.HOME.name
    ) {
        composable(ScreensRoute.HOME.name) {
            Text("screen 1")
        }
        composable(ScreensRoute.INFO.name) {
            Text("screen 2")

        }

    }
}

enum class ScreensRoute {
    HOME, INFO
}


