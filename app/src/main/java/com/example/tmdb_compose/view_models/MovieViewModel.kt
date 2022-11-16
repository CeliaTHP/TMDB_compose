package com.example.tmdb_compose.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb_compose.data.repositories.MovieRepository
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private var movieRepository: MovieRepository,
) : ViewModel() {
    companion object {
        private const val TAG = "MovieViewModel"
    }

    private val popularMovieListLiveData = MutableLiveData<List<Movie>>()
    private val errorLiveData = MutableLiveData<APIError>()

    fun getPopularMovieListLiveData(): MutableLiveData<List<Movie>> {
        return popularMovieListLiveData
    }

    fun getErrorLiveData(): MutableLiveData<APIError> {
        return errorLiveData
    }

    suspend fun getPopularMovieList() {
        movieRepository.getPopularMovies(popularMovieListLiveData, errorLiveData)


    }
}


