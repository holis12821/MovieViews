package com.example.movieviews.presentation.ui.detail_screen

import androidx.lifecycle.Observer
import com.example.movieviews.data.local.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions

@RunWith(MockitoJUnitRunner::class)
class DetailMovieActivityViewModelImplTest {
    //mock object
    private val mMovieRepository = mock<MovieRepository>()
    private val mDetailMovieActivityViewModel = DetailMovieActivityViewModelImpl(mMovieRepository)

    //observer is A simple callback that
    //can receive from LiveData. and onChange to trigger change to UI
    private val mObserverMovie = mock<Observer<MovieEntity>>()

    private val movieList = DataMovieDummy.getMovies()
    private val movieEntity = movieList.firstOrNull()


    /**
     * Things to prepare before the test takes place
     * */
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mDetailMovieActivityViewModel.observeMovie.observeForever(mObserverMovie)
        mDetailMovieActivityViewModel.movie_id = 1
    }

    @Test
    fun getDetailMovie() {


    }


    @After
    fun tearDown() {

        verifyNoMoreInteractions(mMovieRepository)
        clearInvocations(mObserverMovie, mMovieRepository)
    }
}