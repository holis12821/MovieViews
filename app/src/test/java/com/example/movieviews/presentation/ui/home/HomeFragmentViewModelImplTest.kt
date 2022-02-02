package com.example.movieviews.presentation.ui.home

import androidx.lifecycle.Observer
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieViewState
import com.example.movieviews.utils.TestCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mHomeFragmentViewModel = HomeFragmentViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<HomeViewState>>()

    @Captor
    private lateinit var captor: ArgumentCaptor<HomeViewState>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mHomeFragmentViewModel.stateData.observeForever(mObserver)
    }

    /**
     * Showing test when data success
     * */
    @Test
    fun `provide a movie list when successfully obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mHomeFragmentViewModel.getPopularMovie()
            captor.run {
                verify(mObserver, times(3)).onChanged(capture())
                assertThat(allValues[0] is HomeViewState.Init)
                assertThat(allValues[1] is HomeViewState.Loading)
                assertThat(allValues[2] is HomeViewState.SuccessPopularMovie)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data to get error message`() {
        testCoroutineRule.runBlockingTest {
            mHomeFragmentViewModel.getPopularMovie()
            captor.run {
                verify(mObserver, times(3)).onChanged(capture())
                assertThat(allValues[0] is HomeViewState.Init)
                assertThat(allValues[1] is HomeViewState.Message)
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
