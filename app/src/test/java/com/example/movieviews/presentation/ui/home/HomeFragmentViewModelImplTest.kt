package com.example.movieviews.presentation.ui.home

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
import com.example.movieviews.external.dumydata.DataMovieDummy
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import com.example.movieviews.utils.TestCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
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
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mHomeFragmentViewModel = HomeFragmentViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<HomeViewState>>()

    private val response = DataMovieDummy.getMovies().results

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
    fun `Check when successfully obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mHomeFragmentViewModel.getPopularMovie()
            captor.run {
                response?.let { responsePopularMovie ->
                    `when`(mMovieRepository.getPopularMovie(API_KEY, language))
                        .thenReturn(flowOf(responsePopularMovie))
                }
                val listFlowPopular = mMovieRepository.getPopularMovie(API_KEY, language)
                verify(mMovieRepository, atLeastOnce()).getPopularMovie(API_KEY, language)
                verify(mObserver, times(3)).onChanged(capture())
                assertThat(allValues[0] is HomeViewState.Init)
                assertThat(allValues[1] is HomeViewState.Loading)
                assertThat(allValues[2] is HomeViewState.SuccessPopularMovie)
                assertNotNull(listFlowPopular.firstOrNull())
                assertEquals(10, listFlowPopular.firstOrNull()?.size)
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
