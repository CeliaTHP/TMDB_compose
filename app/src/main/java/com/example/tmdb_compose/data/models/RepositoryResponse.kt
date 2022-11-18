package com.example.tmdb_compose.data.models

data class RepositoryResponse(
    val movieList: List<Movie>?,
    val error: APIError?
)
