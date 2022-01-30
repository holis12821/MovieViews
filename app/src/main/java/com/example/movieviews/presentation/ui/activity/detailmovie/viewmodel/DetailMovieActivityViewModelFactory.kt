package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.data.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class DetailMovieActivityViewModelFactory(
    private val repositoryDelegate: MovieRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailMovieActivityViewModelImpl(repositoryDelegate) as T
}