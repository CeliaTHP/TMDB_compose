package com.example.tmdb_compose.data.repositories

import com.example.tmdb_compose.domain.RepositoryResponse

interface MovieRepository {
    companion object {
        private val TAG = "MovieRepository"

    }

    //TODO : Object response with 3 type list ?
    suspend fun getPopularMovies(
    ): RepositoryResponse

    suspend fun getUpcomingMovies(
    ): RepositoryResponse

    suspend fun getTopRatedMovies(
    ): RepositoryResponse


}