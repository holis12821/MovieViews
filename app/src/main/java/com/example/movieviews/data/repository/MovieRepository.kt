package com.example.movieviews.data.repository

import com.example.movieviews.data.models.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
   suspend fun getMovie(): Flow<List<MovieEntity>>
}