package com.example.tmdb_compose.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_compose.data.repositories.MovieRepository
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private var movieRepository: MovieRepository,
    // var state: MovieState
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

    fun getPopularMovieList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movieRepository.getPopularMovies(popularMovieListLiveData, errorLiveData)
            }
        }
    }
}


