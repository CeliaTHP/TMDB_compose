package com.example.tmdb_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdb_compose.data.repositories.FakeMovie
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.view_models.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.items as lazyItems

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = "MainActivity"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //
            val movieViewModel = hiltViewModel<MovieViewModel>()

            //Has to be in the setContent{} to get viewModel ?

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MovieTitle()
                MovieListContent(movieViewModel.state)

            }
            initObservers(movieViewModel)
            initData(movieViewModel)

        }
    }

    @Composable
    fun MovieTitle() {

        Text(
            "Popular",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier =
            Modifier.padding(PaddingValues(20.dp))
        )
    }

    fun initData(movieViewModel: MovieViewModel) {
        movieViewModel.getPopularMovieList()
    }

    fun initObservers(movieViewModel: MovieViewModel) {
        /*
        movieViewModel.getPopularMovieListLiveData().observe(this) {

            Log.d(TAG, "getPopularMovieListObserver $it")
        }
        movieViewModel.getErrorLiveData().observe(this) {
            Log.d(TAG, "getErrorObserver $it")
        }

         */
    }
}


@Composable
fun MovieListContent(state: MovieState) {

    val finalMovieList = FakeMovie.getFakeMovieList()

    //val finalMovieList = state.popularMovieListLiveData.value

    LazyRow(
    ) {
        //is basic compose items but name conflict
        lazyItems(finalMovieList) {
            ItemCardView(it)
        }
    }


}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemCardView(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(300.dp),
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
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            )
            GlideImage(
                fullUrl, "poster",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable(onClick = {
                        //onclick
                    })
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

        }
    }
    /*
    GlideImage(
        model = BuildConfig.BASE_URL + moviePosterPath,
        contentDescription = "poster",
        ) {it.thumbnail()

    }

     */
/*
        Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Bottom,
                Alignment.Start
            ) {

            }
        }

 */
}




