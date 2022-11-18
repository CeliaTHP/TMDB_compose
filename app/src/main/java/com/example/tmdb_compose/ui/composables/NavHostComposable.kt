package com.example.tmdb_compose.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb_compose.domain.Category
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.domain.getCategoryName
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
        startDestination = ScreensRoute.HOME.name,
        modifier =
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        composable(ScreensRoute.HOME.name) {
            Box(modifier = Modifier.padding(PaddingValues(10.dp))) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {

                    MovieRow(Category.POPULAR, movieViewModel, onClick)
                    Spacer(modifier = Modifier.width(16.dp))
                    MovieRow(Category.TOP_RATED, movieViewModel, onClick)
                    Spacer(modifier = Modifier.width(16.dp))
                    MovieRow(Category.UP_COMPING, movieViewModel, onClick)

                }
            }
        }
        composable(ScreensRoute.INFO.name) {
            Column(
                modifier =
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Info()
            }
        }

    }
}

@Composable
fun MovieRow(
    category: Category,
    movieViewModel: MovieViewModel,
    onClick: (movie: Movie) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        CategoryTitle(categoryTitle = getCategoryName(category))
        MovieList(category = category, movieViewModel = movieViewModel, onClick = onClick)
    }

}

@Composable
fun MovieList(category: Category, movieViewModel: MovieViewModel, onClick: (movie: Movie) -> Unit) {
    val state = when (category) {
        Category.POPULAR -> movieViewModel.popularState.collectAsState()
        Category.TOP_RATED -> movieViewModel.topRatedState.collectAsState()
        Category.UP_COMPING -> movieViewModel.upComingState.collectAsState()
    }.value

    LazyRow {
        if (state.isEmpty()) {
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
        items(state) { movie ->
            ItemCardView(movie, onClick)
        }
    }
}


enum class ScreensRoute {
    HOME, INFO
}





