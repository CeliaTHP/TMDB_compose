package com.example.tmdb_compose.ui

import com.example.tmdb_compose.BuildConfig
import com.example.tmdb_compose.MovieServiceAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApiClient {

    var apiClient = provideApiClient()

    private val BASE_URL = BuildConfig.BASE_URL

    //Creating our ApiClient with our interceptor
    private fun provideApiClient(): MovieServiceAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(AuthentificationInterceptor())
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(MovieServiceAPI::class.java)
    }


    //Our interceptor to include access key parameter on each call
    private class AuthentificationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val apiKey = BuildConfig.API_KEY

            return chain.proceed(
                chain.request().newBuilder().url(
                    chain.request().url
                        .newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .build()
                ).build()
            )
        }

    }


}

