package com.example.movieviews.presentation.ui.fragment.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository

class MovieFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : MovieFragmentViewModel, ViewModel() {

    private val _state = MutableLiveData<List<MovieEntity>>()
    val state: LiveData<List<MovieEntity>>
        get() = _state

    override fun getListMovie() {
        _state.value = repositoryDelegate.getMovie()
    }
}



interface MovieFragmentViewModel {
    fun getListMovie()
}