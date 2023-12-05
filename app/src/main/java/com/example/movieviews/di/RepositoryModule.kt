package com.example.movieviews.di

import androidx.paging.ExperimentalPagingApi
import com.example.movieviews.core.DefaultDispatcher
import com.example.movieviews.core.DispatcherProvider
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.data.repository.MovieRepositoryImpl
import com.example.movieviews.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
val repositoryModule = module {
    single { provideMoviesDataSource(get()) }
    single { provideMovieRepository(get(), get()) }
    single { provideDispatcherProvider() }
}

fun provideMoviesDataSource(retrofit: Retrofit): RemoteDataSource {
    return retrofit.create(RemoteDataSource::class.java)
}

@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoroutinesApi
fun provideMovieRepository(
    remoteDataSource: RemoteDataSource,
    dispatcher: DispatcherProvider
): MovieRepository {
    return MovieRepositoryImpl(
        remoteDataSource = remoteDataSource,
        dispatcher = dispatcher
    )
}

fun provideDispatcherProvider(): DispatcherProvider {
    return DefaultDispatcher()
}