package com.example.movieviews.module

import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.domain.repository.MovieRepositoryImpl

object InjectionModule {
    fun provideMovieRepository(): MovieRepository {
        return MovieRepositoryImpl()
    }
}