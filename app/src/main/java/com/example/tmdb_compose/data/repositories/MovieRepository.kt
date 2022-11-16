package com.example.tmdb_compose.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tmdb_compose.data.pojo_models.PopularMovieResponse
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.services.MovieApiClient
import java.io.IOException

class MovieRepository {
    private val TAG = "MovieRepository"

    private val movieApiClient = MovieApiClient().apiClient
    private val popularMovieListLiveData = MutableLiveData<List<Movie>>()

    fun getPopularMovies() {

        try {
            val response = movieApiClient.getPopularMovies().execute()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val popularMovieResponse = response.body()
                    popularMovieListLiveData.postValue(
                        fromMovieResponseListToMovieList(
                            popularMovieResponse
                        )
                    )
                    Log.d(TAG, "getPopularMovies success : $popularMovieResponse")
                }

            } else {
                Log.d(TAG, "getPopularMovies error ")

            }
        } catch (e: IOException) {
            Log.d(TAG, "exception : $e")

        }


    }

    //in viewmodel ?
    fun fromMovieResponseListToMovieList(movieList: PopularMovieResponse?): List<Movie> {
        if (movieList == null)
            return emptyList()

        var finalList = mutableListOf<Movie>()
        for (movie in movieList.results) {
            var movieToAdd = Movie(movie.id, movie.originalTitle, movie.releaseDate, movie.overview)
            if (!(finalList.contains(movieToAdd))) {
                finalList.add(movieToAdd)
                Log.d(TAG, "movie added : $movieToAdd")
            }

        }
        Log.d(TAG, "${finalList.size} final list : $finalList")

        return finalList

    }


}