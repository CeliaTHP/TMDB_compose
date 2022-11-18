package com.example.tmdb_compose.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_compose.data.repositories.MovieRepository
import com.example.tmdb_compose.domain.APIError
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

    private val _errorState = MutableStateFlow(APIError.UNKNOWN_EXCEPTION)
    val errorState: StateFlow<APIError?>
        get() = _errorState

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //call api twice ???
                val repositoryResponse: RepositoryResponse = movieRepository.getPopularMovies()

                //TODO : handle error
                repositoryResponse.movieList?.let {
                    Log.d(TAG, "movieList  :$it")
                    _popularState.value = it
                }

            }
        }
    }
}


