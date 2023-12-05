package com.example.movieviews.domain.repository

import androidx.paging.PagingData
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.data.models.Review
import com.example.movieviews.data.models.Video
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    //remote repository
    suspend fun getPopularMovie(
        apiKey: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getCollectionImage(
        collectionId: Int,
        apiKey: String
    ): Flow<List<Poster>>

    suspend fun getMovieUpcoming(
        apiKey: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getMovieTopRated(
        apiKey: String,
        language: String
    ): Flow<List<MovieResult>>

    suspend fun getTrendingMovie(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): Flow<List<MovieResult>>

    suspend fun getDiscoverMovie(
        currentPage: Int,
        filterBy: String,
        sortBy: String
    ): Flow<PagingData<MovieResult>>

    suspend fun getDiscoverTvShow(): Flow<PagingData<MovieResult>>

    suspend fun getDetailMovie(
        movieId: Int,
        apiKey: String
    ): Flow<MovieResult>

    suspend fun getDetailTvShow(
        tvId: Int,
        apiKey: String
    ): Flow<MovieResult>

    suspend fun getCreditsMovie(
        movieId: Int,
        apiKey: String,
        language: String
    ): Flow<List<Cast>>

    suspend fun getGenres(
        apiKey: String,
        language: String
    ): Flow<List<Genre>>

    suspend fun getVideo(
        movieId: Int,
        apiKey: String,
        language: String
    ): Flow<List<Video>>

    suspend fun getReviewMovie(
        movieId: Int,
        apiKey: String,
        language: String
    ): Flow<List<Review>>

    suspend fun getReviewMoviePagingSource(
        movieId: Int
    ): Flow<PagingData<Review>>
}