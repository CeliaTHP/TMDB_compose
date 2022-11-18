package com.example.tmdb_compose

import android.content.Intent
import android.os.Bundle
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
            val movieViewModel = hiltViewModel<MovieViewModel>()

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            ScaffoldAndNavHost(movieViewModel, navController, scaffoldState, scope) {

                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("movie", it)
                startActivity(intent)

            }

        }
    }


}






