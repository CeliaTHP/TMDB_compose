package com.example.tmdb_compose.domain

data class RepositoryResponse(
    val movieList: List<Movie>?,
    val error: APIError?
)