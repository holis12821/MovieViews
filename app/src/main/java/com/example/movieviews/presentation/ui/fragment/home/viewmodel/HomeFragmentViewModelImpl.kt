package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository

class HomeFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), HomeFragmentViewModel {

    private val _state = MutableLiveData<List<MovieEntity>>()
    val state: LiveData<List<MovieEntity>>
        get() = _state

    override fun getMovie() {
        _state.value = repositoryDelegate.getMovie()
    }
}

interface HomeFragmentViewModel {
    fun getMovie()
}
