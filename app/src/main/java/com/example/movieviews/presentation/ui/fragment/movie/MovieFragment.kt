package com.example.movieviews.presentation.ui.fragment.movie

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.R
import com.example.movieviews.core.BaseFragment
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.FragmentMovieBinding
import com.example.movieviews.external.constant.EXTRA_FILTER
import com.example.movieviews.external.constant.EXTRA_GENRES
import com.example.movieviews.external.constant.EXTRA_MOVIE
import com.example.movieviews.external.constant.EXTRA_SELECTED_SORT
import com.example.movieviews.external.constant.EXTRA_SORT
import com.example.movieviews.external.constant.TYPE
import com.example.movieviews.external.constant.TYPE_FILTER
import com.example.movieviews.external.constant.TYPE_SORT
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.navigateUpWithData
import com.example.movieviews.external.extension.onSetRefreshListener
import com.example.movieviews.external.extension.showToast
import com.example.movieviews.external.extension.swipeGone
import com.example.movieviews.external.extension.swipeVisible
import com.example.movieviews.external.extension.toJson
import com.example.movieviews.external.extension.visible
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.activity.filter.FilterActivity
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
            mAdapterMovieList.refresh()
            mFragmentMovieViewModel.getGenres()
            mFragmentMovieViewModel.getListMovie()
        }

        mBinding?.btnFilter?.setOnClickListener {
            onFilterClick()
        }

        mBinding?.btnSorting?.setOnClickListener {
            onSortClick()
        }
    }

    private fun onSortClick() {
        val intent = Intent(requireContext(), FilterActivity::class.java)
        intent.putExtra(EXTRA_SORT, mFragmentMovieViewModel.sortMovies.toJson())
        intent.putExtra(EXTRA_SELECTED_SORT, mFragmentMovieViewModel.selectedSort)
        intent.putExtra(TYPE, TYPE_SORT)
        updateFilter.launch(intent)
    }

    private fun initData() {
        mBinding?.data = mFragmentMovieViewModel
        mFragmentMovieViewModel.getGenres()
        mFragmentMovieViewModel.getListMovie()
    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterMovieList.maxWidth = 0
        mBinding?.rvMovie?.layoutManager = layoutManager
        mBinding?.rvMovie?.adapter = mAdapterMovieList.withLoadStateFooter(
            footer = MovieLoadStateAdapter { mAdapterMovieList.retry() }
        )
        mAdapterMovieList.addLoadStateListener { loadState ->
            val refreshState = loadState.source.refresh
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
        mFragmentMovieViewModel.state.observe(viewLifecycleOwner) { state ->
            handleState(state)
        }
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

            is MovieViewState.ShowGenres -> onShowGenres(state.genres)
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
        onFilterCount()
        setDataMovieList(pagingData = pagingData)
    }

    private fun onShowGenres(genres: List<Genre>?) {
        mFragmentMovieViewModel.genres = genres
    }

    private fun onFilterCount() {
        var filterCount = 0
        mFragmentMovieViewModel.genres?.forEach { genre ->
            if (mFragmentMovieViewModel.filterGenres.value?.firstOrNull { it.id == genre.id } != null) {
                filterCount += 1
            }
        }

        mFragmentMovieViewModel.filterCount.value = filterCount
    }

    private fun onFilterClick() {
        val intent = Intent(requireContext(), FilterActivity::class.java)
        intent.putExtra(EXTRA_GENRES, mFragmentMovieViewModel.genres.toJson())
        intent.putExtra(EXTRA_FILTER, mFragmentMovieViewModel.filterGenres.value.toJson())
        intent.putExtra(TYPE, TYPE_FILTER)
        updateFilter.launch(intent)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataMovieList(pagingData: PagingData<MovieResult>) {
        mBinding?.rvMovie?.visible()
        mAdapterMovieList.submitData(lifecycle = lifecycle, pagingData = pagingData)
    }

    private val updateFilter =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val genres = Genre.arrayFromData(result.data?.getStringExtra(EXTRA_FILTER))
                val sortBy = result.data?.getStringExtra(EXTRA_SELECTED_SORT)
                mFragmentMovieViewModel.filterGenres.value = genres
                mFragmentMovieViewModel.selectedSort = sortBy
                mFragmentMovieViewModel.currentPage = 1
                mFragmentMovieViewModel.getListMovie()
            }
        }
}