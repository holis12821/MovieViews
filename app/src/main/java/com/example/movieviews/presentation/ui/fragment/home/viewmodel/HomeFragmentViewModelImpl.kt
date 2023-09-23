package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieviews.core.BaseViewModel
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.domain.repository.MovieRepository
import com.example.movieviews.external.constant.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : BaseViewModel(), HomeFragmentViewModel {

    //init state
    private val _stateData = MutableLiveData<HomeViewState>(HomeViewState.Init)
    val stateData: LiveData<HomeViewState> get() = _stateData

    fun onStart() {
        getPopularMovie()
        getCollectionImage()
        getMovieUpcoming()
        getMovieTopRated()
        getTrendingMovie()
    }

    override fun getPopularMovie() {
        viewModelScope.launch {
            repositoryDelegate.getPopularMovie(
                apiKey = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { listPopular ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    successPopularMovie(listPopular)
                }
        }
    }

    override fun getCollectionImage() {
        viewModelScope.launch {
            repositoryDelegate.getCollectionImage(
                collectionId = collectionId,
                apiKey = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { listPoster ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    successPosterMovie(listPoster)
                }
        }
    }

    override fun getMovieUpcoming() {
        viewModelScope.launch {
            repositoryDelegate.getMovieUpcoming(
                apiKey = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { listUpcomingMovie ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    successUpComingMovie(listUpcomingMovie)
                }
        }
    }

    override fun getMovieTopRated() {
        viewModelScope.launch {
            repositoryDelegate.getMovieTopRated(
                apiKey = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { listMovieTopRated ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    successMovieTopRated(listMovieTopRated)
                }
        }
    }

    override fun getTrendingMovie() {
        viewModelScope.launch {
            repositoryDelegate.getTrendingMovie(
                mediaType = MEDIA_TYPE,
                timeWindow = TIME_WINDOW,
                apiKey = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e)
                }
                .collect { listTrendingMovie ->
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    successTrendingMovie(listTrendingMovie)
                }
        }
    }

    override fun showLoading() {
        _stateData.value = HomeViewState.Loading
    }

    override fun hideLoading() {
        _stateData.value = HomeViewState.HideLoading
    }

    override fun showMessage(throwable: Throwable) {
        _stateData.value = HomeViewState.Message(throwable)
    }

    private fun successPosterMovie(listPoster: List<Poster>?) {
        _stateData.value = HomeViewState.PosterMovie(listPoster)
    }

    private fun successPopularMovie(listMoviePopular: List<MovieResult>?) {
        _stateData.value = HomeViewState.SuccessPopularMovie(listPopularMovie = listMoviePopular)
    }

    private fun successUpComingMovie(listUpcomingMovie: List<MovieResult>?) {
        _stateData.value = HomeViewState.SuccessUpcomingMovie(listUpcomingMovie = listUpcomingMovie)
    }

    private fun successMovieTopRated(listTopRatedMovie: List<MovieResult>?) {
        _stateData.value = HomeViewState.SuccessTopRatedMovie(listTopRatedMovie = listTopRatedMovie)
    }

    private fun successTrendingMovie(listTrendingMovie: List<MovieResult>?) {
        _stateData.value = HomeViewState.SuccessTrendingMovie(listTrendingMovie = listTrendingMovie)
    }

}

interface HomeFragmentViewModel {
    fun getPopularMovie()
    fun getCollectionImage()
    fun getMovieUpcoming()
    fun getMovieTopRated()
    fun getTrendingMovie()
}
