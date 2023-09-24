package com.example.movieviews.presentation.ui.activity.detailmovie

import android.content.Intent
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Review
import com.example.movieviews.data.models.Video
import com.example.movieviews.databinding.ActivityDetailMovieBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_KEY_VIDEO
import com.example.movieviews.external.constant.EXTRA_MOVIE
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.example.movieviews.external.constant.EXTRA_URL
import com.example.movieviews.external.constant.TRAILER
import com.example.movieviews.external.constant.TYPE_VIDEO
import com.example.movieviews.external.constant.URL_YOUTUBE
import com.example.movieviews.external.constant.VIDEO
import com.example.movieviews.external.constant.YOUTUBE
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.extension.setSpan
import com.example.movieviews.external.extension.setSpannable
import com.example.movieviews.external.extension.setupHorizontalLayoutManager
import com.example.movieviews.external.extension.setupVerticalLayoutManager
import com.example.movieviews.external.extension.showToast
import com.example.movieviews.external.extension.visible
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieViewState
import com.example.movieviews.presentation.ui.activity.reviewmovie.ReviewListActivity
import com.example.movieviews.presentation.ui.activity.videoview.VideoViewActivity
import com.example.movieviews.presentation.ui.adapter.AdapterReviewMovie
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
    private val mAdapterReviewMovie by lazy { AdapterReviewMovie() }

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
        setupAdapterReviewMovie()

        mBinding.tvSeeMore.setOnClickListener {
            navigateUp()
        }
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
            mActivityDetailMovieViewModel.getVideoMovie()
            mActivityDetailMovieViewModel.getReviewMovie()
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

    private fun setupAdapterReviewMovie() {
        mBinding.rvReviewMovie.adapter = mAdapterReviewMovie
        mBinding.rvReviewMovie.setupVerticalLayoutManager()
    }

    private fun onObserverMovie() {
        mActivityDetailMovieViewModel.state.observe(this) { state ->
            handleState(state)
        }
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
            is DetailMovieViewState.ShowVideo -> onShowVideo(state.videos)
            is DetailMovieViewState.ShowReview -> onShowReviewMovie(state.review)
        }
    }

    private fun onShowVideo(videos: List<Video>?) {
        val videoData =
            videos?.firstOrNull { it.official == true && it.site == YOUTUBE && it.type == TRAILER }
        mBinding.icPlayVideo.isVisible = videoData?.official == true && videoData.site == YOUTUBE
        mBinding.icPlayVideo.setOnClickListener {
            val intent = Intent(this, VideoViewActivity::class.java)
            intent.putExtra(EXTRA_KEY_VIDEO, videoData?.key)
            intent.putExtra(EXTRA_URL, URL_YOUTUBE)
            intent.putExtra(TYPE_VIDEO, VIDEO)
            startActivity(intent)
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

    private fun onShowReviewMovie(reviews: List<Review>?) {
        val filterReview = reviews?.filter { (it.author_details?.rating ?: 0) >= 3 }
        val isShowMovie = !filterReview.isNullOrEmpty()
        mBinding.tvReview.isVisible =  isShowMovie
        mBinding.tvSeeMore.isVisible =  isShowMovie
        mBinding.rvReviewMovie.isVisible = isShowMovie
        mAdapterReviewMovie.setData(filterReview)
    }

    private fun navigateUp() {
        val intent = Intent(this, ReviewListActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, mActivityDetailMovieViewModel.movieId)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}