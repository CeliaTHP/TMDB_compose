package com.example.tmdb_compose.data.repositories

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.example.tmdb_compose.data.pojo_models.PopularMovieResponse
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.services.MovieApiService
import java.io.IOException

class MovieRepositoryImpl(
    private val movieApiClient: MovieApiService
) : MovieRepository {

    companion object {
        private val TAG = "MovieRepositoryImpl"

    }

    override suspend fun getPopularMovies(
        popularMovieListLiveData: MutableState<List<Movie>>,
        errorLiveData: MutableLiveData<APIError>
    ) {
        //TODO : move in constructor

        try {
            val response = movieApiClient.getPopularMovies().execute()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val popularMovieResponse = response.body()
                    popularMovieListLiveData.value =
                        fromMovieResponseListToMovieList(popularMovieResponse)
                    /*
                    popularMovieListLiveData.postValue(
                        fromMovieResponseListToMovieList(
                            popularMovieResponse
                        )


                    )

                     */
                    Log.d(TAG, "getPopularMovies success : $popularMovieResponse")
                }

            } else {
                errorLiveData.postValue(APIError.PARSING_EXCEPTION)
                Log.d(TAG, "getPopularMovies error ")
            }

        } catch (e: IOException) {
            errorLiveData.postValue(APIError.IO_EXCEPTION)
            Log.d(TAG, "exception : $e")
        }
    }

    //in viewmodel ?
    fun fromMovieResponseListToMovieList(movieList: PopularMovieResponse?): List<Movie> {
        if (movieList == null)
            return emptyList()

        var finalList = mutableListOf<Movie>()
        for (movie in movieList.results) {
            var movieToAdd = Movie(
                movie.id,
                movie.originalTitle,
                movie.releaseDate,
                movie.overview,
                movie.posterPath
            )
            if (!(finalList.contains(movieToAdd))) {
                finalList.add(movieToAdd)
                Log.d(TAG, "movie added : $movieToAdd")
            }

        }
        Log.d(TAG, "${finalList.size} final list : $finalList")

        return finalList

    }

}