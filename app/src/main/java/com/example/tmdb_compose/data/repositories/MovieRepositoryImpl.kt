package com.example.tmdb_compose.data.repositories

import android.util.Log
import com.example.tmdb_compose.data.pojo_models.MoviesResponse
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Category
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.domain.RepositoryResponse
import com.example.tmdb_compose.services.MovieApiService
import retrofit2.Response
import java.io.IOException

class MovieRepositoryImpl(
    private val movieApiClient: MovieApiService
) : MovieRepository {

    companion object {
        private val TAG = "MovieRepositoryImpl"

    }

    override suspend fun getMoviesForCategory(category: Category): RepositoryResponse {
        try {
            val moviesResponse = getResponseForCategory(category)
            //val response = movieApiClient.getPopularMovies().execute()
            if (moviesResponse.isSuccessful) {
                if (moviesResponse.body() != null) {
                    val response = moviesResponse.body()
                    Log.d(TAG, "getMovies $category success : $response")
                    return RepositoryResponse(
                        fromMovieResponseListToMovieList(response),
                        null
                    )
                }
            } else {
                return RepositoryResponse(
                    null,
                    APIError.PARSING_EXCEPTION
                )
            }
        } catch (e: IOException) {
            return RepositoryResponse(
                null,
                APIError.IO_EXCEPTION
            )
        }
        return RepositoryResponse(
            null,
            APIError.UNKNOWN_EXCEPTION
        )
    }

    //in viewmodel ?
    fun fromMovieResponseListToMovieList(movieList: MoviesResponse?): List<Movie> {
        if (movieList == null)
            return emptyList()

        var finalList = mutableListOf<Movie>()
        for (movie in movieList.results) {
            var movieToAdd = Movie(
                movie.id,
                movie.title,
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


    private fun getResponseForCategory(category: Category): Response<MoviesResponse> {
        return when (category) {
            Category.POPULAR -> {
                movieApiClient.getPopularMovies().execute()
            }
            Category.TOP_RATED
            -> {
                movieApiClient.getTopRatedMovies().execute()
            }
            Category.UP_COMPING
            -> {
                movieApiClient.getUpcompingMovies().execute()
            }

        }
    }
}