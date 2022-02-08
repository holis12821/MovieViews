package com.example.movieviews.presentation.ui.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.data.repository.remote.MovieRepository
import com.example.movieviews.external.constant.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeFragmentViewModelImpl(
    private val repositoryDelegate: MovieRepository
) : ViewModel(), HomeFragmentViewModel {

    //init state
    private val _stateData = MutableLiveData<HomeViewState>(HomeViewState.Init)
    val stateData: LiveData<HomeViewState> get() = _stateData

    override fun getPopularMovie() {
        viewModelScope.launch {
            repositoryDelegate.getPopularMovie(
                api_key = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listPopular ->
                    EspressoIdlingResource.decrement()
                    successPopularMovie(listPopular)
                }
        }
    }

    override fun getCollectionImage() {
        viewModelScope.launch {
            repositoryDelegate.getCollectionImage(
                collectionId = collectionId,
                api_key = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listPoster ->
                    EspressoIdlingResource.decrement()
                    successPosterMovie(listPoster)
                }
        }
    }

    override fun getMovieUpcoming() {
        viewModelScope.launch {
            repositoryDelegate.getMovieUpcoming(
                api_key = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listUpcomingMovie ->
                    EspressoIdlingResource.decrement()
                    successUpComingMovie(listUpcomingMovie)
                }
        }
    }

    override fun getMovieTopRated() {
        viewModelScope.launch {
            repositoryDelegate.getMovieTopRated(
                api_key = API_KEY,
                language = language
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listMovieTopRated ->
                    EspressoIdlingResource.decrement()
                    successMovieTopRated(listMovieTopRated)
                }
        }
    }

    override fun getTrendingMovie() {
        viewModelScope.launch {
            repositoryDelegate.getTrendingMovie(
                media_type = MEDIA_TYPE,
                time_window = TIME_WINDOW,
                api_key = API_KEY
            ).onStart { showLoading() }
                .catch { e ->
                    showMessage(e)
                }
                .collect { listTrendingMovie ->
                    EspressoIdlingResource.decrement()
                    successTrendingMovie(listTrendingMovie)
                }
        }
    }

    private fun showLoading() {
        _stateData.value = HomeViewState.Loading
    }

    private fun showMessage(throwable: Throwable) {
        _stateData.value = HomeViewState.Message(throwable)
    }

    private fun successPosterMovie(listPoster: List<Poster>) {
        _stateData.value = HomeViewState.PosterMovie(listPoster)
    }

    private fun successPopularMovie(listMoviePopular: List<MovieResult>) {
        _stateData.value = HomeViewState.SuccessPopularMovie(listPopularMovie = listMoviePopular)
    }

    private fun successUpComingMovie(listUpcomingMovie: List<MovieResult>) {
        _stateData.value = HomeViewState.SuccessUpcomingMovie(listUpcomingMovie = listUpcomingMovie)
    }

    private fun successMovieTopRated(listTopRatedMovie: List<MovieResult>) {
        _stateData.value = HomeViewState.SuccessTopRatedMovie(listTopRatedMovie = listTopRatedMovie)
    }

    private fun successTrendingMovie(listTrendingMovie: List<MovieResult>) {
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
