package com.example.tmdb_compose.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.ui.MovieColumns


@Composable
fun NavHost(navController: NavHostController, onClick: (movie: Movie) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoute.HOME.name
    ) {
        composable(ScreensRoute.HOME.name) {
            MovieColumns(onClick)
        }
        composable(ScreensRoute.INFO.name) {
            Info()
        }

    }
}

enum class ScreensRoute {
    HOME, INFO, DETAILS
}



