package com.example.tmdb_compose.services

import com.example.tmdb_compose.data.pojo_models.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

public interface MovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcompingMovies(): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<MoviesResponse>


}