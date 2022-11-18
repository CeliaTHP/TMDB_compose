package com.example.tmdb_compose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdb_compose.BuildConfig
import com.example.tmdb_compose.R
import com.example.tmdb_compose.data.models.Movie

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Details(movie: Movie, onPlayClick: () -> Unit) {
    val fullUrl = BuildConfig.POSTER_URL + movie.posterPath

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            model = fullUrl, contentDescription = "Poster",
            Modifier
                .fillMaxWidth(400f)
                .fillMaxHeight(600f)
                .padding(20.dp)
        )

        Text(movie.title, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)

        Text(
            movie.originalTitle,
            fontSize = 24.sp,
        )

        Text(
            stringResource(id = R.string.release_date, movie.releaseDate),
            fontSize = 20.sp,
        )

        Text(
            movie.overview, fontSize = 18.sp, modifier =
            Modifier.padding(20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .clickable {
                    onPlayClick()
                }
                .padding(50.dp),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(R.drawable.ic_play),
                contentDescription = "Compose Icon",
                Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        onPlayClick()
                    }

            )

        }

    }

}
