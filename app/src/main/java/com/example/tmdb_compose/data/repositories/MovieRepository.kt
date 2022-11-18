package com.example.tmdb_compose.data.repositories

import com.example.tmdb_compose.data.models.Category
import com.example.tmdb_compose.data.models.RepositoryResponse

interface MovieRepository {
    companion object {
        private val TAG = "MovieRepository"

    }

    suspend fun getMoviesForCategory(
        category: Category
    ): RepositoryResponse


}