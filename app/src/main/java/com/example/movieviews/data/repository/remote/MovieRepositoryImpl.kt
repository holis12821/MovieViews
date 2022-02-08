package com.example.movieviews.data.repository.remote

import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.external.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getPopularMovie(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getPopularMovie(
                    api_key = api_key,
                    language = language
                )
                data.results?.let { listMovie ->
                    emit(listMovie)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCollectionImage(
        collectionId: Int,
        api_key: String
    ): Flow<List<Poster>> {
        return flow {
            try {
                val data = remoteDataSource.getCollectionImage(
                    collectionId = collectionId,
                    api_key = api_key
                )
                data.posters?.let { posters ->
                    emit(posters)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieUpcoming(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getMovieUpcoming(
                    api_key = api_key,
                    language = language
                )
                data.results?.let { listMovie ->
                    emit(listMovie)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieTopRated(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getMovieTopRated(
                    api_key = api_key,
                    language = language
                )
                data.results?.let { listMovie ->
                    emit(listMovie)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTrendingMovie(
        media_type: String,
        time_window: String,
        api_key: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getTrendingMovie(
                    media_type = media_type,
                    time_window = time_window,
                    api_key = api_key
                )
                data.results?.let { listTrendingMovie ->
                    emit(listTrendingMovie)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDiscoverMovie(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getDiscoverMovie(
                    api_key = api_key,
                    language = language
                )
                data.results?.let { listMovie ->
                    emit(listMovie)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDiscoverTvShow(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getDiscoverTvShow(
                    api_key = api_key,
                    language = language
                )
                data.results?.let { listMovie ->
                    emit(listMovie)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailMovie(movie_id: Int, api_key: String): Flow<DetailMovieEntity> {
        return flow {
            try {
                val data = remoteDataSource.getDetailMovie(
                    movie_id = movie_id,
                    api_key = api_key
                )
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailTvShow(tv_id: Int, api_key: String): Flow<DetailMovieEntity> {
        return flow {
            try {
                val data = remoteDataSource.getDetailTvShow(
                    tv_id = tv_id,
                    api_key = api_key
                )
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCreditsMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ): Flow<List<Cast>> {
        return flow {
            try {
                val data = remoteDataSource.getCreditsMovie(
                    movie_id = movie_id,
                    api_key = api_key,
                    language = language
                )
                data.cast?.let { listCast ->
                    emit(listCast)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}