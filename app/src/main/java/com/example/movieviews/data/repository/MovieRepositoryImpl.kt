package com.example.movieviews.data.repository

import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.models.TvShowEntity
import com.example.movieviews.external.dumydata.DataMovieDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl: MovieRepository {

    override suspend fun getMovie(): Flow<List<MovieEntity>> {
        return flow {
            try {
                val dataDummyMovie = DataMovieDummy.getMovies()
                delay(1_000)
                emit(dataDummyMovie)
            } catch (e: Throwable) {
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.Default)
    }

    override suspend fun getTvShow(): Flow<List<TvShowEntity>> {
        return flow {
            try {
                val dataDummyTvShow = DataMovieDummy.getTvShow()
                delay(1_000)
                emit(dataDummyTvShow)
            } catch (e: Throwable) {
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.Default)
    }

}