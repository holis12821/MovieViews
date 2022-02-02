package com.example.movieviews.presentation.ui.movie

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieViewState
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
class MovieFragmentViewModelImplTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mMovieFragmentViewModel = MovieFragmentViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<MovieViewState>>()

    @Captor
    private lateinit var captor: ArgumentCaptor<MovieViewState>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mMovieFragmentViewModel.state.observeForever(mObserver)
    }

    /**
     * Showing test when data success
     * */
    @Test
    fun `provide a movie list when successfully obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mMovieFragmentViewModel.getListMovie()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is MovieViewState.Init)
                Truth.assertThat(allValues[1] is MovieViewState.Loading)
                Truth.assertThat(allValues[2] is MovieViewState.SuccessDiscoverMovie)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data to get error message`() {
        testCoroutineRule.runBlockingTest {
            mMovieFragmentViewModel.getListMovie()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is MovieViewState.Init)
                //check if the message you got will generate an error message
                Truth.assertThat(allValues[1] is MovieViewState.Message)
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