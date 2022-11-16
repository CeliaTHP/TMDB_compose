package com.example.tmdb_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdb_compose.ui.theme.TMDB_composeTheme
import com.example.tmdb_compose.view_models.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = "MainActivity"
    }

    private val scope = CoroutineScope(newSingleThreadContext("MainActivityThread"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val movieViewModel = hiltViewModel<MovieViewModel>()

            TMDB_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
            //Has to be in the setContent{} to get viewModel ?
            initObservers(movieViewModel)
            initData(movieViewModel)


        }

        //   initData()
    }

    fun initData(movieViewModel: MovieViewModel) {
        scope.launch {
            movieViewModel.getPopularMovieList()
        }
    }

    fun initObservers(movieViewModel: MovieViewModel) {
        movieViewModel.getPopularMovieListLiveData().observe(this) {
            Log.d(TAG, "getPopularMovieListObserver $it")
        }
        movieViewModel.getErrorLiveData().observe(this) {
            Log.d(TAG, "getErrorObserver $it")
        }
    }

}

