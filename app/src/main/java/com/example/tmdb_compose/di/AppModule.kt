package com.example.tmdb_compose.di

import com.example.tmdb_compose.data.repositories.MovieRepository
import com.example.tmdb_compose.data.repositories.MovieRepositoryImpl
import com.example.tmdb_compose.services.MovieApiService
import com.example.tmdb_compose.view_models.MovieViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieRepository(movieApiService: MovieApiService): MovieRepository {
        return MovieRepositoryImpl(movieApiService)

    }

    @Provides
    @Singleton
    fun provideMovieViewModel(movieRepository: MovieRepository): MovieViewModel {
        return MovieViewModel(movieRepository)
    }


}
