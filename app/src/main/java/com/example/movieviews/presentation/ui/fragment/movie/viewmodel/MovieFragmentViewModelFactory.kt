package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.data.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class MovieFragmentViewModelFactory(
    private val repositoryDelegate: MovieRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieFragmentViewModelImpl(repositoryDelegate) as T
}