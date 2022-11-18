package com.example.tmdb_compose.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_compose.data.repositories.MovieRepository
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Category
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.domain.RepositoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _popularState = MutableStateFlow(emptyList<Movie>())
    val popularState: StateFlow<List<Movie>>
        get() = _popularState

    private val _topRatedState = MutableStateFlow(emptyList<Movie>())
    val topRatedState: StateFlow<List<Movie>>
        get() = _topRatedState

    private val _upComingState = MutableStateFlow(emptyList<Movie>())
    val upComingState: StateFlow<List<Movie>>
        get() = _upComingState


    private val _errorState = MutableStateFlow(APIError.UNKNOWN_EXCEPTION)
    val errorState: StateFlow<APIError?>
        get() = _errorState

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d(TAG, "initViewModel")

                //API Call for popular movies
                val popularResponse: RepositoryResponse = movieRepository.getMoviesForCategory(
                    Category.POPULAR
                )

                popularResponse.movieList?.let {
                    Log.d(TAG, "popular movieList  :$it")
                    _popularState.value = it
                }

                //API Call for top rated movies
                val topRatedResponse: RepositoryResponse =
                    movieRepository.getMoviesForCategory(Category.TOP_RATED)

                topRatedResponse.movieList?.let {
                    Log.d(TAG, "top Rated movieList  :$it")
                    _topRatedState.value = it
                }

                //API Call for upcoming movies
                val upComingResponse: RepositoryResponse =
                    movieRepository.getMoviesForCategory(Category.UP_COMPING)

                upComingResponse.movieList?.let {
                    Log.d(TAG, "up coming movieList  :$it")
                    _upComingState.value = it
                }

            }
        }
    }
}


