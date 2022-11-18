package com.example.tmdb_compose.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdb_compose.BuildConfig
import com.example.tmdb_compose.data.models.Movie
import com.example.tmdb_compose.ui.composables.NavHost
import com.example.tmdb_compose.ui.composables.ScreensRoute
import com.example.tmdb_compose.ui.composables.drawer.DrawerBody
import com.example.tmdb_compose.ui.composables.drawer.DrawerHeader
import com.example.tmdb_compose.ui.composables.drawer.MenuItem
import com.example.tmdb_compose.view_models.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun CategoryTitle(categoryTitle: String) {

    Text(
        categoryTitle,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier =
        Modifier
            .padding(PaddingValues(10.dp))
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemCardView(movie: Movie, onClick: (movie: Movie) -> Unit) {
    Box(modifier = Modifier
        .clickable {
            onClick(movie)
        }) {

        Card(
            modifier = Modifier
                .padding(6.dp)
                .height(300.dp)
                .width(200.dp),
            elevation = 6.dp,
            backgroundColor = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(corner = CornerSize(4.dp))

        ) {
            val moviePosterPath =
                movie.posterPath
            val fullUrl = BuildConfig.POSTER_URL + moviePosterPath
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = movie.title,
                    modifier = Modifier
                        .padding(PaddingValues(10.dp)),

                    style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                )
                GlideImage(
                    fullUrl, "poster",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = {
                            //add onclick here because it doesnt triggers the onClick on Box
                            onClick(movie)
                        })
                        .fillMaxWidth()
                        .fillMaxHeight()
                )

            }
        }
    }
}


@Composable
fun ScaffoldAndNavHost(
    movieViewModel: MovieViewModel,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    onClick: (movie: Movie) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )

        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(ScreensRoute.HOME, "Home", Icons.Default.Home),
                    MenuItem(ScreensRoute.INFO, "Infos", Icons.Default.Info),

                    ), onItemClick = {
                    //Switch view
                    navController.navigate(it.id.name) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                    Log.d("ITEM_CLICK", "menuItem closed : ${it.title}")
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }

                }
            )
        },
        content = {

        NavHost(movieViewModel, navController, onClick)
        })
}


