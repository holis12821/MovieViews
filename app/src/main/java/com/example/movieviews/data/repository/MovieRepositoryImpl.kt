package com.example.movieviews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieviews.core.DispatcherProvider
import com.example.movieviews.data.local.LocalDb
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.data.remote.MoviePagingSource
import com.example.movieviews.data.remote.MovieRemoteMediator
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.external.constant.PAGE_SIZE
import com.example.movieviews.external.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val db: LocalDb,
    private val dispatcher: DispatcherProvider
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
        }.flowOn(dispatcher.io)
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
        }.flowOn(dispatcher.io)
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
        }.flowOn(dispatcher.io)
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
        }.flowOn(dispatcher.io)
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
        }.flowOn(dispatcher.io)
    }


    override suspend fun getDiscoverMovie(): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, movieFlags = true) }
        ).flow
    }

    override suspend fun getDiscoverTvShow(): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, movieFlags = false) }
        ).flow
    }

    override suspend fun getDetailMovie(movie_id: Int, api_key: String): Flow<MovieResult> {
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
        }.flowOn(dispatcher.io)
    }

    override suspend fun getDetailTvShow(tv_id: Int, api_key: String): Flow<MovieResult> {
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
        }.flowOn(dispatcher.io)
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
        }.flowOn(dispatcher.io)
    }

    override suspend fun getMovieFromDb(): Flow<PagingData<MovieResult>> {
        val pagingSourceFactory = {db.getFavoriteMovieDao().getAll() }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @ExperimentalPagingApi
    override suspend fun getMovieFromMediator(): Flow<PagingData<MovieResult>> {
        val pagingSourceFactory = { db.getFavoriteMovieDao().getAll() }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            remoteMediator = MovieRemoteMediator(
                remoteDataSource,
                db = db
            ),
             pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun isFavorite(id: Int): Flow<MovieResult> {
        return flow {
            try {
                val data = db.getFavoriteMovieDao().isFavorite(id)
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(dispatcher.io)
    }

}