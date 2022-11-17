package com.example.tmdb_compose.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdb_compose.BuildConfig
import com.example.tmdb_compose.domain.Movie

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Details(movie: Movie) {
    val fullUrl = BuildConfig.POSTER_URL + movie.posterPath

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            model = fullUrl, contentDescription = "Poster", Modifier
                .fillMaxWidth(300f)
                .fillMaxHeight(500f)
                .padding(20.dp)
        )
        Text(movie.originalTitle, fontSize = 40.sp)
        Text(
            movie.overview, fontSize = 16.sp, modifier =
            Modifier.padding(20.dp)
        )


    }


}
