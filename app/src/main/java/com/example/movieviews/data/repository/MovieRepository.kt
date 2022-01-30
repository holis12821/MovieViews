package com.example.movieviews.data.repository

import com.example.movieviews.data.models.MovieEntity

interface MovieRepository {
    fun getMovie(): List<MovieEntity>
}