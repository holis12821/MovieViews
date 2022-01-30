package com.example.movieviews.data.repository

import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.external.dumydata.DataMovieDummy

class MovieRepositoryImpl : MovieRepository {

    override fun getMovie(): List<MovieEntity> = DataMovieDummy.getMovies()
}