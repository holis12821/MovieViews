package com.example.movieviews.presentation.ui.activity.reviewmovie

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.data.models.Review
import com.example.movieviews.databinding.ActivityReviewListBinding
import com.example.movieviews.external.constant.EXTRA_MOVIE
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.setupVerticalLayoutManager
import com.example.movieviews.external.extension.showToast
import com.example.movieviews.external.extension.visible
import com.example.movieviews.presentation.ui.activity.reviewmovie.viewmodel.ReviewListViewModelImpl
import com.example.movieviews.presentation.ui.activity.reviewmovie.viewmodel.ReviewListViewState
import com.example.movieviews.presentation.ui.adapter.MovieLoadStateAdapter
import com.example.movieviews.presentation.ui.adapter.ReviewPagingDataAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewListActivity : BaseActivity<ActivityReviewListBinding>() {
    override fun getResLayoutId(): Int = R.layout.activity_review_list

    private val mActivityReviewListViewModel by viewModel<ReviewListViewModelImpl>()

    private val mAdapterReviewList by lazy { ReviewPagingDataAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        setupShowToolbar()
        setupView()
    }

    private fun setupView() {
        onInitState()
        initData()
        initAdapter()
    }

    private fun initData() {
        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
        mActivityReviewListViewModel.movieId = movieId
        mActivityReviewListViewModel.getReviewList()
    }

    private fun initAdapter() {
        mBinding.rvReviewMovie.setupVerticalLayoutManager()
        mBinding.rvReviewMovie.adapter = mAdapterReviewList.withLoadStateFooter(
            footer = MovieLoadStateAdapter { mAdapterReviewList.retry() }
        )
        mAdapterReviewList.addLoadStateListener { loadState ->
            val refreshState = loadState.source.refresh
            mBinding.rvReviewMovie.isVisible = refreshState is LoadState.NotLoading
            mBinding.progressBar.isVisible = refreshState is LoadState.Loading
            mBinding.btnRetry.isVisible = refreshState is LoadState.Error
            handleError(loadState)
        }
        mBinding.btnRetry.setOnClickListener {
            mAdapterReviewList.retry()
        }
    }

    private fun onObserver() {
        mActivityReviewListViewModel.state.observe(this) { state ->
            handleState(state)
        }
    }

    private fun handleState(state: ReviewListViewState) {
        when (state) {
            is ReviewListViewState.Init -> onInitState()
            is ReviewListViewState.Loading -> onProgress()
            is ReviewListViewState.HideLoading -> onHideProgress()
            is ReviewListViewState.Message -> onShowMessage(state.throwable.message.toString())
            is ReviewListViewState.ShowReviewList -> state.pagingData?.let { pagingData ->
                onSuccessReviewMovie(
                    pagingData = pagingData
                )
            }
        }
    }

    private fun setupShowToolbar() {
        supportActionBar?.title = getString(R.string.review_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onInitState() {
        mBinding.rvReviewMovie.gone()
    }

    private fun onProgress() {
        showDialogProgress()
    }

    private fun onHideProgress() {
        hideProgress()
    }

    private fun onShowMessage(message: String) {
        mBinding.rvReviewMovie.gone()
        showToast(this, message = message)
    }

    private fun onSuccessReviewMovie(pagingData: PagingData<Review>) {
        setDataReviewList(pagingData = pagingData)
    }

    private fun setDataReviewList(pagingData: PagingData<Review>) {
        mBinding.rvReviewMovie.visible()
        mAdapterReviewList.submitData(lifecycle = lifecycle, pagingData = pagingData)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}