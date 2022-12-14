package com.example.tmdb_compose.data.pojo_models

import com.squareup.moshi.Json

//@JsonClass(generateAdapter = true)
data class MovieResponse(
    @field:Json(name = "backdrop_path") val backdropPath: String,
    @field:Json(name = "genre_ids") val genreIds: List<Int>,
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterPath: String,
    @field:Json(name = "release_date") val releaseDate: String,

    //Get infos about gender with another API Call
    @field:Json(name = "title") val title: String,
    @field:Json(name = "video") val video: Boolean,
    @field:Json(name = "vote_average") val vote_average: Double,
    @field:Json(name = "vote_count") val vote_count: Long,


    )