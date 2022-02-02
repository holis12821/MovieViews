package com.example.movieviews.presentation.ui.tvshow

import androidx.lifecycle.Observer
import com.example.movieviews.data.repository.MovieRepository
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.language
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
class TvShowFragmentViewModelImplTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mMovieRepository = mock<MovieRepository>()
    private val mTvShowFragmentViewModel = TvShowFragmentViewModelImpl(mMovieRepository)
    private val mObserver = mock<Observer<TvShowViewState>>()

    @Captor
    private lateinit var captor: ArgumentCaptor<TvShowViewState>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testCoroutineRule.onStart()
        mTvShowFragmentViewModel.state.observeForever(mObserver)
    }

    /**
     * Showing test when data success
     * */
    @Test
    fun `provide a movie list when successfully obtained from the repository on viewModel`() {
        testCoroutineRule.runBlockingTest {
            mTvShowFragmentViewModel.getTvShowList()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is TvShowViewState.Init)
                Truth.assertThat(allValues[1] is TvShowViewState.Loading)
                Truth.assertThat(allValues[2] is TvShowViewState.SuccessDiscoverTvShow)
                clearInvocations(mObserver)
            }
        }
    }

    @Test
    fun `Should fail when fetchFrom data to get error message`() {
        testCoroutineRule.runBlockingTest {
            mTvShowFragmentViewModel.getTvShowList()
            captor.run {
                Mockito.verify(mObserver, Mockito.times(3)).onChanged(capture())
                Truth.assertThat(allValues[0] is TvShowViewState.Init)
                //check if the message you got will generate an error message
                Truth.assertThat(allValues[1] is TvShowViewState.Message)
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