package com.example.tmdb_compose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.tmdb_compose.domain.Movie

class MovieState {
    val popularMovieListLiveData: MutableState<List<Movie>> = mutableStateOf(listOf())

}
