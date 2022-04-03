package com.example.movieviews.presentation.ui.fragment.tvshow

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.R
import com.example.movieviews.core.BaseFragment
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.FragmentTvShowBinding
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.example.movieviews.external.extension.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieLoadStateAdapter
import com.example.movieviews.presentation.ui.adapter.MoviePagingDataAdapter
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : BaseFragment<FragmentTvShowBinding>() {

    override fun getResLayoutId(): Int = R.layout.fragment_tv_show

    private val mFragmentTvShowViewModel by viewModel<TvShowFragmentViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mAdapterTvShowList by lazy {
        MoviePagingDataAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {

                override fun onItemClickCallback(data: MovieResult) {
                    requireContext().navigateUpWithData(
                        activity = DetailMovieActivity::class.java,
                        key = EXTRA_TV_SHOW_MOVIE,
                        data = data
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
            mFragmentTvShowViewModel.getTvShowList()
        }
    }

    private fun initData() {
        mFragmentTvShowViewModel.getTvShowList()
    }

    private fun initAdapter(
        isMediator: Boolean = false
    ) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterTvShowList.maxWidth = 0
        mBinding?.rvTvShow?.layoutManager = layoutManager
        mBinding?.rvTvShow?.adapter = mAdapterTvShowList.withLoadStateFooter(
            footer = MovieLoadStateAdapter { mAdapterTvShowList.retry() }
        )
        mAdapterTvShowList.addLoadStateListener { loadState ->
            val refreshState = if (isMediator) {
                loadState.mediator?.refresh
            } else {
                loadState.source.refresh
            }
            mBinding?.rvTvShow?.isVisible = refreshState is LoadState.NotLoading
            mBinding?.progressBar?.isVisible = refreshState is LoadState.Loading
            mBinding?.btnRetry?.isVisible = refreshState is LoadState.Error
            handleError(loadState)
        }
        mBinding?.btnRetry?.setOnClickListener { mAdapterTvShowList.retry() }
    }

    private fun onObserver() {
        mFragmentTvShowViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: TvShowViewState) {
        when (state) {
            is TvShowViewState.Init -> onInitState()
            is TvShowViewState.Loading -> onProgress()
            is TvShowViewState.HideLoading -> onHideProgress()
            is TvShowViewState.Message -> onShowMessage(state.throwable.message.toString())
            is TvShowViewState.SuccessDiscoverTvShow -> state.data?.let { pagingData ->
                onSuccessDiscoverTvShow(
                    pagingData
                )
            }
        }
    }

    private fun onInitState() {
        mBinding?.rvTvShow?.gone()
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
        mBinding?.rvTvShow?.gone()
        showToast(requireContext(), message = message)
    }

    private fun onSuccessDiscoverTvShow(pagingData: PagingData<MovieResult>) {
        setDataTvShowList(pagingData)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataTvShowList(pagingData: PagingData<MovieResult>) {
        mBinding?.rvTvShow?.visible()
        mAdapterTvShowList.submitData(lifecycle, pagingData)
    }
}