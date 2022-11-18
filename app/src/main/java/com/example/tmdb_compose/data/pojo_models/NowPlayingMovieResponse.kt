package com.example.tmdb_compose.data.pojo_models

import com.squareup.moshi.Json

data class NowPlayingMovieResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<MovieResponse>,

    ) {}