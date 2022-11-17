package com.example.tmdb_compose.domain

data class Movie(
    val id: Long,
    val originalTitle: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String
) {

}