package com.example.movieviews.di

import com.example.movieviews.core.DefaultDispatcher
import com.example.movieviews.core.DispatcherProvider
import com.example.movieviews.data.local.LocalDb
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val repositoryModule = module {
    single { provideMoviesDataSource(get()) }
    single { provideMovieRepository(get(), get(), get()) }
    single { provideDispatcherProvider() }
}

fun provideMoviesDataSource(retrofit: Retrofit): RemoteDataSource {
    return retrofit.create(RemoteDataSource::class.java)
}

@ExperimentalCoroutinesApi
fun provideMovieRepository(
    remoteDataSource: RemoteDataSource,
    db: LocalDb,
    dispatcher: DispatcherProvider
): MovieRepository {
    return MovieRepositoryImpl(
        remoteDataSource = remoteDataSource,
        db = db,
        dispatcher = dispatcher
    )
}

fun provideDispatcherProvider(): DispatcherProvider {
    return DefaultDispatcher()
}