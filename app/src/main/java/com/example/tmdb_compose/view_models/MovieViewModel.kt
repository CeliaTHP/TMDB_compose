package com.example.tmdb_compose.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_compose.data.repositories.MovieRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository,
) : ViewModel() {

    fun test() {
        viewModelScope
    }
}


