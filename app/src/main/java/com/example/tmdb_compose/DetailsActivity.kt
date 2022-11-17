package com.example.tmdb_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.ui.composables.Details

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getParcelableExtra<Movie>("movie") != null) {
            val movie = intent.getParcelableExtra<Movie>("movie") as Movie
            setContent {
                Details(movie)
            }
        }

    }
}