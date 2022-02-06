package com.example.movieviews.presentation.ui.activity.detailmovie

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieviews.R
import com.example.movieviews.data.local.CastEntity
import com.example.movieviews.data.models.Cast
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.databinding.ActivityDetailMovieBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.example.movieviews.external.extension.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieViewState
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.CastAdapterMovie
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDetailMovieBinding
    private val mActivityDetailMovieViewModel by viewModel<DetailMovieActivityViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mProgressDialog by lazy { ProgressDialog(this) }

    private val mAdapterCastMovie by lazy {
        CastAdapterMovie().apply {
            listener = object : AdapterClickListener<CastEntity> {
                override fun onItemClickCallback(data: CastEntity) {
//                  coming soon
                }

                override fun onViewClickCallback(view: View, data: CastEntity) {
//                  coming soon
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EspressoIdlingResource.increment()
        setupLayoutDetailMovie()
        initView()
        onObserverMovie()
    }

    private fun setupLayoutDetailMovie() {
        mBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
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
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        val tvShowId = intent.getIntExtra(EXTRA_TV_SHOW_MOVIE, 0)
        val detailMovieFlags = intent.getBooleanExtra(EXTRA_DATAIl_MOVIE, false)
        if (detailMovieFlags) {
            title = getString(R.string.detailMovie)
            mActivityDetailMovieViewModel.movieId = movieId
            mActivityDetailMovieViewModel.getDetailMovie()
            mActivityDetailMovieViewModel.getCastMovie()
        }
        else {
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
            is DetailMovieViewState.Loading -> onProgress()
            is DetailMovieViewState.Message -> onShowMessage(state.throwable.message.toString())
            is DetailMovieViewState.ShowDetailMovie -> onShowDetailMovie(state.detailMovieEntity)
            is DetailMovieViewState.ShowCastMovie -> onShowCastMovie(state.listCastMovie)
        }
    }

    private fun onInitState() {
        mBinding.clContent.gone()
    }

    private fun onProgress() {
        mProgressDialog.show()
    }

    private fun onHideProgress() {
        mProgressDialog.dismiss()
    }

    private fun onShowMessage(message: String) {
        onHideProgress()
        Toast.makeText(
            this,
            message, Toast.LENGTH_SHORT
        ).show()
    }

    private fun onShowDetailMovie(detailMovieEntity: DetailMovieEntity) {
        onHideProgress()
        mBinding.clContent.visible()
        showDetailMovie(detailMovieEntity)
    }

    private fun showDetailMovie(detailMovieEntity: DetailMovieEntity?) {
        val imageSizeLarge = getString(R.string.original)
        val imageSizeSmall = getString(R.string.w500)
        val imageUrlBanner = "$BASE_URL_IMAGE$imageSizeLarge/${detailMovieEntity?.backdropPath}"
        val imageUrlPoster = "$BASE_URL_IMAGE$imageSizeSmall/${detailMovieEntity?.posterPath}"
        mBinding.apply {
            ivPosterImage.setImage(imageUrlBanner)
            ivPosterMovie.setImage(imageUrlPoster)
            if (!detailMovieEntity?.originalTitle.isNullOrEmpty()) tvTitleMovie.text = detailMovieEntity?.originalTitle
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
            mBinding.rvBilledCast.gone()
            mBinding.tvTopBilledCast.gone()
        } else {
            mBinding.rvBilledCast.visible()
            mBinding.tvTopBilledCast.visible()
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

    override fun onDestroy() {
        super.onDestroy()
        try {
            mProgressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}