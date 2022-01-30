package com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieviews.data.models.CastEntity
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy

class DetailMovieActivityViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : DetailMovieActivityViewModel, ViewModel() {

    private val _observeMovie = MutableLiveData<MovieEntity>()
    val observeMovie: LiveData<MovieEntity>
        get() = _observeMovie

    private val _observeCastMovie = MutableLiveData<List<CastEntity>>()
    val observeCastMovie: LiveData<List<CastEntity>>
        get() = _observeCastMovie

    var id: Int = 0

    override fun getDetailMovie() {
        showDetailMovie(
            repositoryDelegate.getMovie()
        )
    }

    private fun showDetailMovie(list: List<MovieEntity>) {
        val movieEntity = list.firstOrNull { it.id == id }
        movieEntity?.let { entityMovie ->
            _observeMovie.value = entityMovie
        }
        showCastMovieList(movieEntity?.cast)
    }

    private fun showCastMovieList(listCast: List<CastEntity>?) {
        listCast?.let { castList ->
            _observeCastMovie.value = castList
        }
    }
}

interface DetailMovieActivityViewModel {
    fun getDetailMovie()
}