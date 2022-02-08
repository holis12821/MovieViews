package com.example.movieviews.data.repository.remote

import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.DetailMovieEntity
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

    suspend fun getDiscoverMovie(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getDiscoverTvShow(
        api_key: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getDetailMovie(
        movie_id: Int,
        api_key: String
    ): Flow<DetailMovieEntity>

    suspend fun getDetailTvShow(
        tv_id : Int,
        api_key: String
    ): Flow<DetailMovieEntity>

    suspend fun getCreditsMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ): Flow<List<Cast>>
}