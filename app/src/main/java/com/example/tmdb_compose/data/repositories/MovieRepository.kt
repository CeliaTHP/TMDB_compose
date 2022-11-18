package com.example.tmdb_compose.data.repositories

import com.example.tmdb_compose.domain.RepositoryResponse

interface MovieRepository {
    companion object {
        private val TAG = "MovieRepository"

    }

    suspend fun getPopularMovies(
    ): RepositoryResponse


}