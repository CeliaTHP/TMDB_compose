package com.example.tmdb_compose.data.repositories

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Movie

interface MovieRepository {
    companion object {
        private val TAG = "MovieRepository"

    }

    suspend fun getPopularMovies(
        popularMovieListLiveData: MutableState<List<Movie>>,
        errorLiveData: MutableLiveData<APIError>
    )


}