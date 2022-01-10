package com.example.movieviews.domain.repository

import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.utils.DataMovieDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl: MovieRepositoryDelegate {

    override suspend fun getMovie(): Flow<List<MovieEntity>> {
        return flow {
            try {
                val dataDummyMovie = DataMovieDummy.getMovies()
                emit(dataDummyMovie)
            } catch (e: Throwable) {
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.Default)
    }

}