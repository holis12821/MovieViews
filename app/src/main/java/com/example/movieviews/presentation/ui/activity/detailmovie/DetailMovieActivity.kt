package com.example.movieviews.presentation.ui.activity.detailmovie

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.R
import com.example.movieviews.data.models.CastEntity
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.ActivityDetailMovieBinding
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.*
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelFactory
import com.example.movieviews.presentation.ui.activity.detailmovie.viewmodel.DetailMovieActivityViewModelImpl
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.CastAdapterMovie

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDetailMovieBinding
    private lateinit var mActivityDetailMovieViewModel: DetailMovieActivityViewModelImpl

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
//    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }

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
        setupLayoutDetailMovie()
        initView()
        onObserverMovie()
        onObserverTvShow()
    }

    private fun setupLayoutDetailMovie() {
        mBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initView() {
        mActivityDetailMovieViewModel = ViewModelProvider(
            this,
            DetailMovieActivityViewModelFactory(
                InjectionModule.provideMovieRepository()
            )
        )[DetailMovieActivityViewModelImpl::class.java]
        setupShowToolbar()
        initData()
        setupAdapterCastMovie()
    }

    private fun setupShowToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        mActivityDetailMovieViewModel.id = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mActivityDetailMovieViewModel.getDetailMovie()
    }

    private fun setupAdapterCastMovie() {
        mAdapterCastMovie.maxWidth = 125
        mBinding.rvBilledCast.adapter = mAdapterCastMovie
        mBinding.rvBilledCast.setupHorizontalLayoutManager()
    }

    private fun onObserverMovie() {
        mActivityDetailMovieViewModel.observeMovie.observe(this, { observeMovie ->
            onDataChangeMovie(observeMovie)
        })
    }

    private fun onObserverTvShow() {
        mActivityDetailMovieViewModel.observeCastMovie.observe(
            this,
            { observeCastMovie ->
                onDataChangeCastMovie(observeCastMovie)
            })
    }

    private fun onDataChangeMovie(detailMovieEntity: MovieEntity?) {
        onShowDetailMovie(detailMovieEntity)
    }

    private fun onShowDetailMovie(detailMovieEntity: MovieEntity?) {
        showDetailMovie(detailMovieEntity)
    }

    private fun showDetailMovie(detailMovieEntity: MovieEntity?) {
        mBinding.apply {
            ivPosterImage.setImage(detailMovieEntity?.posterUrl)
            ivPosterMovie.setImage(detailMovieEntity?.posterUrl)
            tvTitleMovie.text = detailMovieEntity?.title
            tvScoreFilm.text = getString(
                R.string.user_score,
                detailMovieEntity?.rating.toString()
            )
            tvMovieCertification.setSpan(
                textFirst = detailMovieEntity?.certification,
                textSecond = detailMovieEntity?.duration
            )
            tvGenre.setSpannable(detailMovieEntity?.genres?.toTypedArray())
            tvOverview.text = getString(R.string.overview)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    mBinding.tvDescOverview.justificationMode = JUSTIFICATION_MODE_INTER_WORD
                }
            }
            tvDescOverview.text = detailMovieEntity?.overview
            if (detailMovieEntity?.tagLine.isNullOrEmpty()) tvTagline.text = "-"
            else tvTagline.text = detailMovieEntity?.tagLine
        }
    }

    private fun onDataChangeCastMovie(list: List<CastEntity>) {
        onShowCastMovie(list)
    }

    private fun onShowCastMovie(list: List<CastEntity>?) {
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
    private fun setDataCastMovie(list: List<CastEntity>) {
        mAdapterCastMovie.setData(list)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    /*override fun onDestroy() {
        super.onDestroy()
        try {
            mProgressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}