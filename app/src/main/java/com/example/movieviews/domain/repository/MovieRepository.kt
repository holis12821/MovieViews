package com.example.movieviews.domain.repository

import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.models.TvShowEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
   suspend fun getMovie(): Flow<List<MovieEntity>>
}