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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdb_compose.BuildConfig
import com.example.tmdb_compose.data.repositories.FakeMovie
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.ui.composables.DrawerBody
import com.example.tmdb_compose.ui.composables.DrawerHeader
import com.example.tmdb_compose.ui.composables.MenuItem
import com.example.tmdb_compose.ui.composables.NavHost
import com.example.tmdb_compose.ui.composables.ScreensRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MovieColumn(categoryTitle: String, onClick: (movie: Movie) -> Unit) {
    Column(
    ) {
        CategoryTitle(categoryTitle)
        MovieListContent(onClick)
    }

}

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


@Composable
fun MovieListContent(onClick: (movie: Movie) -> Unit) {

    val finalMovieList = FakeMovie.getFakeMovieList()

    //val finalMovieList = state.popularMovieListLiveData.value

    LazyRow(

    ) {
        //is basic compose items but name conflict

        items(finalMovieList) { movie ->
            ItemCardView(movie, onClick)


        }
    }


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
                .padding(10.dp)
                .height(300.dp)
                .width(200.dp),

            //  .width(200.dp)
            //.height(300.dp),
            elevation = 6.dp,
            backgroundColor = Color.Blue,
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
                    text = movie.originalTitle,
                    modifier = Modifier
                        .padding(PaddingValues(6.dp)),

                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                )
                GlideImage(
                    fullUrl, "poster",
                    modifier = Modifier
                        .padding(20.dp)
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
            NavHost(navController = navController, onClick)

        })
}


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