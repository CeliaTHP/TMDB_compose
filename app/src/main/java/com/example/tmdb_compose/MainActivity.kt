package com.example.tmdb_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdb_compose.ui.AppBar
import com.example.tmdb_compose.ui.PopularColumn
import com.example.tmdb_compose.ui.composables.DrawerBody
import com.example.tmdb_compose.ui.composables.DrawerHeader
import com.example.tmdb_compose.ui.composables.MenuItem
import com.example.tmdb_compose.view_models.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            ScaffoldAndContent("Popular", scaffoldState, scope)


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

@Composable
fun ScaffoldAndContent(categoryTitle: String, scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )

        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem("home", "Home", Icons.Default.Home),
                    MenuItem("infos", "Infos", Icons.Default.Info),

                    ), onItemClick = {
                    //Switch view
                    Log.d("ITEM_CLICK", "menuItem closed : ${it.title}")
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }

                }
            )
        },
        content = {
            PopularColumn(categoryTitle)
        }
    )
}






