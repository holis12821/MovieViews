package com.example.movieviews.di

import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.data.repository.MovieRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { provideMoviesDataSource(get()) }
    single { provideMovieRepository(get()) }
}

fun provideMoviesDataSource(retrofit: Retrofit): RemoteDataSource {
    return retrofit.create(RemoteDataSource::class.java)
}

fun provideMovieRepository(remoteDataSource: RemoteDataSource): MovieRepository {
    return MovieRepositoryImpl(remoteDataSource = remoteDataSource)
}