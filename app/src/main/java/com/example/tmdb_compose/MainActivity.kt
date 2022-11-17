package com.example.tmdb_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tmdb_compose.ui.AppBar
import com.example.tmdb_compose.ui.MovieColumn
import com.example.tmdb_compose.ui.composables.DrawerBody
import com.example.tmdb_compose.ui.composables.DrawerHeader
import com.example.tmdb_compose.ui.composables.MenuItem
import com.example.tmdb_compose.ui.composables.NavHost
import com.example.tmdb_compose.ui.composables.ScreensRoute
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

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            ScaffoldAndContent(navController, scaffoldState, scope)


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
fun ScaffoldAndContent(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
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
                    MenuItem(ScreensRoute.HOME, "Home", Icons.Default.Home),
                    MenuItem(ScreensRoute.INFO, "Infos", Icons.Default.Info),

                    ), onItemClick = {
                    //Switch view
                    navController.navigate(it.id.name) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                    Log.d("ITEM_CLICK", "menuItem closed : ${it.title}")
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }

                }
            )
        },
        content = {
            NavHost(navController = navController)
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())

            ) {
                MovieColumn("Popular")
                MovieColumn("Top Rated")
                MovieColumn("Now Playing")
            }


        }
    )
}






