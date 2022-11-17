package com.example.tmdb_compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdb_compose.BuildConfig
import com.example.tmdb_compose.data.repositories.FakeMovie
import com.example.tmdb_compose.domain.Movie


@Composable
fun PopularColumn(categoryTitle: String) {
    Column(
    ) {
        CategoryTitle(categoryTitle)
        MovieListContent()
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
fun MovieListContent() {

    val finalMovieList = FakeMovie.getFakeMovieList()

    //val finalMovieList = state.popularMovieListLiveData.value

    LazyRow(
    ) {
        //is basic compose items but name conflict
        items(finalMovieList) {
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
}