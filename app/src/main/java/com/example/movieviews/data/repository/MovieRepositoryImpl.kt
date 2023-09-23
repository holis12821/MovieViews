package com.example.movieviews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieviews.core.DispatcherProvider
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.data.models.Video
import com.example.movieviews.data.remote.MoviePagingSource
import com.example.movieviews.data.remote.RemoteDataSource
import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.external.constant.PAGE_SIZE
import com.example.movieviews.external.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource, private val dispatcher: DispatcherProvider
) : MovieRepository {
    override suspend fun getPopularMovie(
        apiKey: String, language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getPopularMovie(
                    api_key = apiKey, language = language
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
        collectionId: Int, apiKey: String
    ): Flow<List<Poster>> {
        return flow {
            try {
                val data = remoteDataSource.getCollectionImage(
                    collectionId = collectionId, api_key = apiKey
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
        apiKey: String, language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getMovieUpcoming(
                    api_key = apiKey, language = language
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
        apiKey: String, language: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getMovieTopRated(
                    api_key = apiKey, language = language
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
        mediaType: String, timeWindow: String, apiKey: String
    ): Flow<List<MovieResult>> {
        return flow {
            try {
                val data = remoteDataSource.getTrendingMovie(
                    media_type = mediaType, time_window = timeWindow, api_key = apiKey
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


    override suspend fun getDiscoverMovie(
        currentPage: Int, filterBy: String, sortBy: String
    ): Flow<PagingData<MovieResult>> {
        return Pager(config = PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
            enablePlaceholders = false
        ), pagingSourceFactory = {
            MoviePagingSource(
                remoteDataSource,
                movieFlags = true,
                filterBy = filterBy,
                sortBy = sortBy,
                currentPage = currentPage,
            )
        }).flow
    }

    override suspend fun getDiscoverTvShow(): Flow<PagingData<MovieResult>> {
        return Pager(config = PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
            enablePlaceholders = false
        ),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, movieFlags = false) }).flow
    }

    override suspend fun getDetailMovie(movieId: Int, apiKey: String): Flow<MovieResult> {
        return flow {
            try {
                val data = remoteDataSource.getDetailMovie(
                    movie_id = movieId, api_key = apiKey
                )
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(dispatcher.io)
    }

    override suspend fun getDetailTvShow(tvId: Int, apiKey: String): Flow<MovieResult> {
        return flow {
            try {
                val data = remoteDataSource.getDetailTvShow(
                    tv_id = tvId, api_key = apiKey
                )
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(dispatcher.io)
    }

    override suspend fun getCreditsMovie(
        movieId: Int, apiKey: String, language: String
    ): Flow<List<Cast>> {
        return flow {
            try {
                val data = remoteDataSource.getCreditsMovie(
                    movie_id = movieId, api_key = apiKey, language = language
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

    override suspend fun getGenres(apiKey: String, language: String): Flow<List<Genre>> {
        return flow {
            try {
                val data = remoteDataSource.getGenres(api_key = apiKey, language = language)
                data.genres?.let { genres ->
                    emit(genres)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(dispatcher.io)
    }

    override suspend fun getVideo(
        movieId: Int, apiKey: String, language: String
    ): Flow<List<Video>> {
        return flow {
            try {
                val data = remoteDataSource.getVideoMovie(
                    movie_id = movieId, api_key = apiKey, language = language
                )
                data.results?.let { videos ->
                    emit(videos)
                }
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(dispatcher.io)
    }
}