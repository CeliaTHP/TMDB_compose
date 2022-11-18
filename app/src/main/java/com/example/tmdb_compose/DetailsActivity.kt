package com.example.tmdb_compose

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tmdb_compose.data.models.Movie
import com.example.tmdb_compose.ui.composables.Details

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getParcelableExtra<Movie>("movie") != null) {
            val movie = intent.getParcelableExtra<Movie>("movie") as Movie
            setContent {
                Details(movie) {
                    var soa = "1ltMedGeRvg"
                    watchYoutubeVideo(this, soa)
                }
            }
        }

    }
}

fun watchYoutubeVideo(context: Context, id: String) {
    val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
    val webIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://www.youtube.com/watch?v=$id")
    )
    try {
        context.startActivity(appIntent)
    } catch (ex: ActivityNotFoundException) {
        context.startActivity(webIntent)
    }
}

