package com.example.tmdb_compose.services

import com.example.tmdb_compose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieApiClient {

    //Creating our ApiClient with our interceptor
    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(AuthentificationInterceptor())
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(MovieApiService::class.java)
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

