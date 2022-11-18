package com.example.tmdb_compose.services

import com.example.tmdb_compose.data.pojo_models.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

public interface MovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<CategoryResponse>

    @GET("movie/upcoming")
    fun getUpcompingMovies(): Call<CategoryResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<CategoryResponse>


}