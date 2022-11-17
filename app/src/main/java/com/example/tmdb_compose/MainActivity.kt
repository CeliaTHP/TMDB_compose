package com.example.tmdb_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.tmdb_compose.ui.ScaffoldAndNavHost
import com.example.tmdb_compose.view_models.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = "MainActivity"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //
            val movieViewModel = hiltViewModel<MovieViewModel>()

            //Has to be in the setContent{} to get viewModel ?
            initObservers(movieViewModel)
            initData(movieViewModel)

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            ScaffoldAndNavHost(navController, scaffoldState, scope) {
                Log.d(TAG, it.toString())
            }

        }
    }

    fun initData(movieViewModel: MovieViewModel) {
        movieViewModel.getPopularMovieList()
    }

    fun initObservers(movieViewModel: MovieViewModel) {
        movieViewModel.getPopularMovieListLiveData().observe(this) {
            //MovieListContent()
            Log.d(TAG, "getPopularMovieListObserver $it")
        }
        movieViewModel.getErrorLiveData().observe(this) {
            Log.d(TAG, "getErrorObserver $it")
        }

    }

}






