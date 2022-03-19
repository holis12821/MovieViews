package com.example.movieviews.presentation.ui.activity.detailmovie

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.ActivityDetailMovieBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_MOVIE
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.example.movieviews.external.extension.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieViewState
import com.example.movieviews.presentation.ui.adapter.CastAdapterMovie
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_detail_movie

    private val mActivityDetailMovieViewModel by viewModel<DetailMovieActivityViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mAdapterCastMovie by lazy { CastAdapterMovie() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        EspressoIdlingResource.increment()
        initView()
        onObserverMovie()
    }

    private fun initView() {
        setupShowToolbar()
        initData()
        onInitState()
        setupAdapterCastMovie()
    }

    private fun setupShowToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        mBinding.viewModel = mActivityDetailMovieViewModel
        val movie = intent.getParcelableExtra<MovieResult>(EXTRA_MOVIE)
        val movieId = movie?.id ?: 0
        val tvShow = intent.getParcelableExtra<MovieResult>(EXTRA_TV_SHOW_MOVIE)
        val tvShowId = tvShow?.id ?: 0
        mActivityDetailMovieViewModel.detailMovieFlags =
            intent.getBooleanExtra(EXTRA_DATAIl_MOVIE, false)
        if (mActivityDetailMovieViewModel.detailMovieFlags) {
            title = getString(R.string.detailMovie)
            mActivityDetailMovieViewModel.movieId = movieId
            mActivityDetailMovieViewModel.getDetailMovie()
            mActivityDetailMovieViewModel.getCastMovie()
        } else {
            title = getString(R.string.detailTvShow)
            mActivityDetailMovieViewModel.tvShowId = tvShowId
            mActivityDetailMovieViewModel.getDetailTvShow()
        }
    }

    private fun setupAdapterCastMovie() {
        mAdapterCastMovie.maxWidth = 125
        mBinding.rvBilledCast.adapter = mAdapterCastMovie
        mBinding.rvBilledCast.setupHorizontalLayoutManager()
    }

    private fun onObserverMovie() {
        mActivityDetailMovieViewModel.state.observe(this, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: DetailMovieViewState) {
        when (state) {
            is DetailMovieViewState.Init -> onInitState()
            is DetailMovieViewState.Loading -> showDialogProgress()
            is DetailMovieViewState.HideLoading -> hideProgress()
            is DetailMovieViewState.Message -> showToast(
                this,
                message = state.throwable.message.toString()
            )
            is DetailMovieViewState.ShowDetailMovie -> onShowDetailMovie(state.detailMovieEntity)
            is DetailMovieViewState.ShowCastMovie -> onShowCastMovie(state.listCastMovie)
        }
    }

    private fun onInitState() {
        mBinding.clContent.gone()
    }

    private fun onShowDetailMovie(detailMovieEntity: MovieResult) {
        mBinding.clContent.visible()
        showDetailMovie(detailMovieEntity)
    }

    private fun showDetailMovie(detailMovieEntity: MovieResult?) {
        val imageSizeLarge = getString(R.string.original)
        val imageSizeSmall = getString(R.string.w500)
        val imageUrlBanner = "$BASE_URL_IMAGE$imageSizeLarge/${detailMovieEntity?.backdropPath}"
        val imageUrlPoster = "$BASE_URL_IMAGE$imageSizeSmall/${detailMovieEntity?.posterPath}"
        mBinding.apply {
            ivPosterImage.setImage(imageUrlBanner)
            ivPosterMovie.setImage(imageUrlPoster)
            if (!detailMovieEntity?.originalTitle.isNullOrEmpty()) tvTitleMovie.text =
                detailMovieEntity?.originalTitle
            else tvTitleMovie.text = detailMovieEntity?.originalName
            tvScoreFilm.text = getString(
                R.string.user_score,
                detailMovieEntity?.voteAverage.toString()
            )
            if (detailMovieEntity?.releaseDate.isNullOrEmpty()) {
                tvMovieCertification.setSpan(
                    textFirst = detailMovieEntity?.originalLanguage,
                    "-"
                )
            } else {
                tvMovieCertification.setSpan(
                    textFirst = detailMovieEntity?.originalLanguage,
                    textSecond = detailMovieEntity?.releaseDate
                )
            }
            tvGenre.setSpannable(detailMovieEntity?.genres)
            tvOverview.text = getString(R.string.overview)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    mBinding.tvDescOverview.justificationMode = JUSTIFICATION_MODE_INTER_WORD
                }
            }
            tvDescOverview.text = detailMovieEntity?.overview
            if (detailMovieEntity?.tagline.isNullOrEmpty()) tvTagline.text = "-"
            else tvTagline.text = detailMovieEntity?.tagline
        }
    }


    private fun onShowCastMovie(list: List<Cast>?) {
        if (list.isNullOrEmpty()) {
            mBinding.tvTopBilledCast.gone()
            mBinding.rvBilledCast.gone()
        } else {
            mBinding.tvTopBilledCast.visible()
            mBinding.rvBilledCast.visible()
            mBinding.tvTopBilledCast.text = getString(R.string.top_billed_cast)
            setDataCastMovie(list)
        }
    }

    /**
     * A Function set data cast movie into adapter
     * */
    private fun setDataCastMovie(list: List<Cast>) {
        mAdapterCastMovie.setData(list)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}