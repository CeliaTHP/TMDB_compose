package com.example.tmdb_compose.data.pojo_models

import com.squareup.moshi.Json

//@JsonClass(generateAdapter = true)
data class PopularMovieResponse(

    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<MovieResponse>,
    @field:Json(name = "total_pages") val totalPages: Long,
    @field:Json(name = "total_results") val totalResults: Long

)