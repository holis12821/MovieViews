package com.example.movieviews.presentation.ui.fragment.detail_screen.detail_movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.data.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class DetailMovieFragmentViewModelFactory(
    private val repositoryDelegate: MovieRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailMovieFragmentViewModelImpl(repositoryDelegate) as T
}