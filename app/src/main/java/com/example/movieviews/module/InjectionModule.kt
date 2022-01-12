package com.example.movieviews.module

import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.data.repository.MovieRepositoryImpl

object InjectionModule {
    fun provideMovieRepository(): MovieRepository {
        return MovieRepositoryImpl()
    }
}