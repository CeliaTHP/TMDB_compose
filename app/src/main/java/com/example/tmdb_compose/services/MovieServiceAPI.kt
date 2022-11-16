package com.example.tmdb_compose.services

import com.example.tmdb_compose.data.pojo_models.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET

public interface MovieServiceAPI {

    @GET("movie/now_playing")
    fun getNowPlayingMovies() {

    }

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMovieResponse>

    @GET("movie/latest")
    fun getLatestMovies() {

    }

    @GET("/movie/top_rated")
    fun getTopRatedMovies() {

    }


}