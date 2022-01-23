package com.example.movieviews.data.repository

import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.external.dumydata.DataMovieDummy
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
   suspend fun getMovie(
      movieList: List<MovieEntity>
   ): Flow<List<MovieEntity>>
}