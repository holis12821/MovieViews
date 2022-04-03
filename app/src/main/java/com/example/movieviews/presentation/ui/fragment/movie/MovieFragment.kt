package com.example.movieviews.presentation.ui.fragment.movie

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.R
import com.example.movieviews.core.BaseFragment
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.FragmentMovieBinding
import com.example.movieviews.external.constant.EXTRA_MOVIE
import com.example.movieviews.external.extension.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieLoadStateAdapter
import com.example.movieviews.presentation.ui.adapter.MoviePagingDataAdapter
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    override fun getResLayoutId(): Int = R.layout.fragment_movie

    private val mFragmentMovieViewModel by viewModel<MovieFragmentViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mAdapterMovieList by lazy {
        MoviePagingDataAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {
                override fun onItemClickCallback(data: MovieResult) {
                    requireContext().navigateUpWithData(
                        activity = DetailMovieActivity::class.java,
                        key = EXTRA_MOVIE,
                        data = data,
                        flags = true
                    )
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieResult
                ) {

                }

            }
        }
    }

    override fun onViewCreated() {
        EspressoIdlingResource.increment()
        initView()
        onObserver()
    }

    private fun initView() {
        setupView()
        onInitState()
        initData()
        initAdapter()
    }

    private fun setupView() {
        mBinding?.swipeRefresh?.onSetRefreshListener {
            mFragmentMovieViewModel.getListMovie()
        }
    }

    private fun initData() {
        mBinding?.viewModel = mFragmentMovieViewModel
        mFragmentMovieViewModel.getListMovie()
    }

    private fun initAdapter(
        isMediator: Boolean = false
    ) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterMovieList.maxWidth = 0
        mBinding?.rvMovie?.layoutManager = layoutManager
        mBinding?.rvMovie?.adapter = mAdapterMovieList.withLoadStateFooter(
            footer = MovieLoadStateAdapter { mAdapterMovieList.retry() }
        )
        mAdapterMovieList.addLoadStateListener { loadState ->
            val refreshState = if (isMediator) {
                loadState.mediator?.refresh
            } else {
                loadState.source.refresh
            }
            mBinding?.rvMovie?.isVisible = refreshState is LoadState.NotLoading
            mBinding?.progressBar?.isVisible = refreshState is LoadState.Loading
            mBinding?.btnRetry?.isVisible = refreshState is LoadState.Error
            handleError(loadState)
        }
        mBinding?.btnRetry?.setOnClickListener {
            mAdapterMovieList.retry()
        }
    }

    private fun onObserver() {
        mFragmentMovieViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: MovieViewState) {
        when (state) {
            is MovieViewState.Init -> onInitState()
            is MovieViewState.Loading -> onProgress()
            is MovieViewState.HideLoading -> onHideProgress()
            is MovieViewState.Message -> onShowMessage(state.throwable.message.toString())
            is MovieViewState.SuccessDiscoverMovie -> state.pagingData?.let { pagingData ->
                onSuccessDiscoverMovieList(
                    pagingData = pagingData
                )
            }
        }
    }

    private fun onInitState() {
        mBinding?.rvMovie?.gone()
    }

    private fun onProgress() {
        showDialogProgress()
        mBinding?.swipeRefresh?.swipeVisible()
    }

    private fun onHideProgress() {
        hideProgress()
        mBinding?.swipeRefresh?.swipeGone()
    }

    private fun onShowMessage(message: String) {
        mBinding?.swipeRefresh?.swipeGone()
        mBinding?.rvMovie?.gone()
        showToast(requireContext(), message = message)
    }

    private fun onSuccessDiscoverMovieList(pagingData: PagingData<MovieResult>) {
        setDataMovieList(pagingData = pagingData)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataMovieList(pagingData: PagingData<MovieResult>) {
        mBinding?.rvMovie?.visible()
        mAdapterMovieList.submitData(lifecycle = lifecycle, pagingData = pagingData)
    }
}