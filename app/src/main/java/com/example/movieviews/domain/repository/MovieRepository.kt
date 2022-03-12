package com.example.movieviews.domain.repository

import androidx.paging.PagingData
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    //remote repository
    suspend fun getPopularMovie(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getCollectionImage(
        collectionId: Int,
        api_key: String
    ): Flow<List<Poster>>

    suspend fun getMovieUpcoming(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getMovieTopRated(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getTrendingMovie(
        media_type: String,
        time_window: String,
        api_key: String
    ): Flow<List<MovieResult>>

    suspend fun getDiscoverMovie(): Flow<PagingData<MovieResult>>

    suspend fun getDiscoverTvShow(): Flow<PagingData<MovieResult>>

    suspend fun getDetailMovie(
        movie_id: Int,
        api_key: String
    ): Flow<MovieResult>

    suspend fun getDetailTvShow(
        tv_id: Int,
        api_key: String
    ): Flow<MovieResult>

    suspend fun getCreditsMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ): Flow<List<Cast>>

    suspend fun getMovieFromDb(): Flow<PagingData<MovieResult>>
    suspend fun getMovieFromMediator(): Flow<PagingData<MovieResult>>

    suspend fun isFavorite(id: Int): Flow<MovieResult>

}