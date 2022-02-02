package com.example.movieviews.presentation.ui.detail_screen

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieViewState
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieViewState
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowViewState
import com.example.movieviews.utils.TestCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieActivityViewModelImplTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mDetailActivityMovieViewModel = DetailMovieActivityViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<DetailMovieViewState>>()

    @Captor
    private lateinit var captor: ArgumentCaptor<DetailMovieViewState>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mDetailActivityMovieViewModel.state.observeForever(mObserver)
    }

    /**
     * Showing test when data success
     * */
    @Test
    fun `provide a movie list when successfully get movie detail obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailMovie()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                Truth.assertThat(allValues[1] is DetailMovieViewState.Loading)
                Truth.assertThat(allValues[2] is DetailMovieViewState.ShowDetailMovie)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `provide a movie list when successfully get tv show movie obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailTvShow()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                Truth.assertThat(allValues[1] is DetailMovieViewState.Loading)
                Truth.assertThat(allValues[2] is DetailMovieViewState.ShowDetailMovie)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data detail movie to get error message`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailMovie()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                //check if the message you got will generate an error message
                Truth.assertThat(allValues[1] is DetailMovieViewState.Message)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data tv show to get error message`() {
        testCoroutineRule.runBlockingTest {
            mDetailActivityMovieViewModel.getDetailTvShow()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is DetailMovieViewState.Init)
                //check if the message you got will generate an error message
                Truth.assertThat(allValues[1] is DetailMovieViewState.Message)
                clearInvocations(mObserver)
            }
        }
    }

    @After
    fun tearDown() {
        testCoroutineRule.onStop()
        clearInvocations(mMovieRepository)
    }
}