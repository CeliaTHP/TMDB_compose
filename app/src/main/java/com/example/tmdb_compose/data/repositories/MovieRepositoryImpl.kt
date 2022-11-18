package com.example.tmdb_compose.data.repositories

import android.util.Log
import com.example.tmdb_compose.data.pojo_models.MoviesResponse
import com.example.tmdb_compose.domain.APIError
import com.example.tmdb_compose.domain.Movie
import com.example.tmdb_compose.domain.RepositoryResponse
import com.example.tmdb_compose.services.MovieApiService
import java.io.IOException

class MovieRepositoryImpl(
    private val movieApiClient: MovieApiService
) : MovieRepository {

    companion object {
        private val TAG = "MovieRepositoryImpl"

    }

    override suspend fun getPopularMovies(): RepositoryResponse {
        try {
            val response = movieApiClient.getPopularMovies().execute()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val popularMovieResponse = response.body()
                    Log.d(TAG, "getPopularMovies success : $popularMovieResponse")
                    return RepositoryResponse(
                        fromMovieResponseListToMovieList(popularMovieResponse),
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

    override suspend fun getUpcomingMovies(): RepositoryResponse {
        try {
            val response = movieApiClient.getUpcompingMovies().execute()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val popularMovieResponse = response.body()
                    Log.d(TAG, "getPopularMovies success : $popularMovieResponse")
                    return RepositoryResponse(
                        fromMovieResponseListToMovieList(popularMovieResponse),
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

    override suspend fun getTopRatedMovies(): RepositoryResponse {
        try {
            val response = movieApiClient.getTopRatedMovies().execute()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val popularMovieResponse = response.body()
                    Log.d(TAG, "getTopRatedMovies success : $popularMovieResponse")
                    return RepositoryResponse(
                        fromMovieResponseListToMovieList(popularMovieResponse),
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

}