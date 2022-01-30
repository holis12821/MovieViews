package com.example.movieviews.presentation.ui.detail_screen

import androidx.lifecycle.Observer
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.utils.InstantRuleExecution
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

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
        InstantRuleExecution.onStart()
        mDetailMovieActivityViewModel.observeMovie.observeForever(mObserverMovie)
        mDetailMovieActivityViewModel.id = 1
    }

    @Test
    fun getDetailMovie() {

        //given
        Mockito.`when`(mMovieRepository.getMovie())
            .thenReturn(movieList)

        //when
        mDetailMovieActivityViewModel.getDetailMovie()

        //then
        verify(mMovieRepository).getMovie()
        verify(mObserverMovie).onChanged(movieEntity)
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.cast)
        assertEquals(movieEntity.id, mDetailMovieActivityViewModel.id)
    }


    @After
    fun tearDown() {
        InstantRuleExecution.onStop()
        verifyNoMoreInteractions(mMovieRepository)
        clearInvocations(mObserverMovie, mMovieRepository)
    }
}