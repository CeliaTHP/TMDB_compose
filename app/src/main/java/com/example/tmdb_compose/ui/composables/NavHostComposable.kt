package com.example.tmdb_compose.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.ui.CategoryTitle
import com.example.tmdb_compose.ui.ItemCardView
import com.example.tmdb_compose.view_models.MovieViewModel


@Composable
fun NavHost(
    movieViewModel: MovieViewModel,
    navController: NavHostController,
    onClick: (movie: Movie) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoute.HOME.name
    ) {
        /*
        composable(ScreensRoute.HOME.name) {
            MovieColumns(onClick)
        }
         */
        composable(ScreensRoute.HOME.name) {
            MovieRow("Populars", movieViewModel, onClick)
        }
        composable(ScreensRoute.INFO.name) {
            Info()
        }

    }
}

@Composable
fun MovieRow(
    categoryTitle: String,
    movieViewModel: MovieViewModel,
    onClick: (movie: Movie) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        CategoryTitle(categoryTitle = categoryTitle)
        MovieList(movieViewModel = movieViewModel, onClick = onClick)
    }

}

@Composable
fun MovieList(movieViewModel: MovieViewModel, onClick: (movie: Movie) -> Unit) {
    val popularState by movieViewModel.popularState.collectAsState()

    LazyRow {

        if (popularState.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier
                    = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
        }

        //is basic compose items but name conflict
        items(popularState) { movie ->
            ItemCardView(movie, onClick)
        }
    }
}

/*
@Composable
fun MovieColumns(onClick: (movie: Movie) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())

    ) {

        MovieColumn("Popular", onClick)
        MovieColumn("Top Rated", onClick)
        MovieColumn("Now Playing", onClick)

    }
}

 */

enum class ScreensRoute {
    HOME, INFO
}





