package com.example.movieviews.presentation.ui.fragment.home

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.movieviews.R
import com.example.movieviews.core.BaseFragment
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.databinding.FragmentHomeBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.*
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mFragmentViewModel by viewModel<HomeFragmentViewModelImpl>()

    override fun getResLayoutId(): Int = R.layout.fragment_home

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instances object,
     * if it is already it will be usable
     * */
    private val mAdapterPopular by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {
                override fun onItemClickCallback(data: MovieResult) {
                    requireContext().navigateUpWithData(
                        activity = DetailMovieActivity::class.java,
                        key = EXTRA_MOVIE_ID,
                        data = data.id,
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

    private val mAdapterTopRatingMovie by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {
                override fun onItemClickCallback(data: MovieResult) {
                    requireContext().navigateUpWithData(
                        activity = DetailMovieActivity::class.java,
                        key = EXTRA_MOVIE_ID,
                        data = data.id,
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

    private val mAdapterTrendingMovie by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {
                override fun onItemClickCallback(data: MovieResult) {
                    requireContext().navigateUpWithData(
                        activity = DetailMovieActivity::class.java,
                        key = EXTRA_MOVIE_ID,
                        data = data.id,
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

    private val mAdapterUpComingMovie by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {
                override fun onItemClickCallback(data: MovieResult) {
                    requireContext().navigateUpWithData(
                        activity = DetailMovieActivity::class.java,
                        key = EXTRA_MOVIE_ID,
                        data = data.id,
                        flags = true
                    )
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieResult,
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
        initData()
        onInitState()
        setupView()
        setupAdapterPopularMovie()
        setupAdapterFreeWatch()
        setupAdapterTrendingMovie()
        setupAdapterUpComingMovie()
    }

    private fun setupView() {
        //setup image in drawable
        mBinding?.apply {
            ivLogo.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_tmdb_logo
                )
            )
            tvMovieDb.text = getString(R.string.app_name)
            tvSeeAllPopular.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.coming_soon),
                    Toast.LENGTH_SHORT
                ).show()
            }
            tvSeeAllTopRatedMovie.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.coming_soon),
                    Toast.LENGTH_SHORT
                ).show()
            }
            tvSeeAllTrending.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.coming_soon),
                    Toast.LENGTH_SHORT
                ).show()
            }
            tvSeeAllUpcomingMovie.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.coming_soon),
                    Toast.LENGTH_SHORT
                ).show()
            }

            swipeRefresh.onSetRefreshListener {
                mFragmentViewModel.onStart()
            }
        }
    }

    private fun initData() {
        mBinding?.viewModel = mFragmentViewModel
        mFragmentViewModel.onStart()
    }

    private fun setupAdapterPopularMovie() {
        mAdapterPopular.maxWidth = 160
        mBinding?.rvPopularMovie?.adapter = mAdapterPopular
        mBinding?.rvPopularMovie?.setupHorizontalLayoutManager()
    }

    private fun setupAdapterFreeWatch() {
        mAdapterTopRatingMovie.maxWidth = 160
        mBinding?.rvTopRatedMovie?.adapter = mAdapterTopRatingMovie
        mBinding?.rvTopRatedMovie?.setupHorizontalLayoutManager()
    }

    private fun setupAdapterTrendingMovie() {
        mAdapterTrendingMovie.maxWidth = 160
        mBinding?.rvTrendingMovie?.adapter = mAdapterTrendingMovie
        mBinding?.rvTrendingMovie?.setupHorizontalLayoutManager()
    }

    private fun setupAdapterUpComingMovie() {
        mAdapterUpComingMovie.maxWidth = 160
        mBinding?.rvUpcomingMovie?.adapter = mAdapterUpComingMovie
        mBinding?.rvUpcomingMovie?.setupHorizontalLayoutManager()
    }

    private fun onObserver() {
        mFragmentViewModel.stateData.observe(viewLifecycleOwner, { stateData ->
            handleState(stateData)
        })
    }

    private fun handleState(stateData: HomeViewState) {
        when (stateData) {
            is HomeViewState.Init -> onInitState()
            is HomeViewState.Loading -> onProgress()
            is HomeViewState.HideLoading -> onHideProgress()
            is HomeViewState.Message -> onShowMessage(stateData.throwable.message.toString())
            is HomeViewState.PosterMovie -> onSuccessPosterMovie(stateData.listPoster)
            is HomeViewState.SuccessPopularMovie -> onSuccessMoviePopular(stateData.listPopularMovie)
            is HomeViewState.SuccessTopRatedMovie -> onSuccessMovieTopRating(stateData.listTopRatedMovie)
            is HomeViewState.SuccessTrendingMovie -> onSuccessTrendingMovie(stateData.listTrendingMovie)
            is HomeViewState.SuccessUpcomingMovie -> onSuccessDataMovieUpComing(stateData.listUpcomingMovie)
        }
    }


    private fun onInitState() {
        mBinding?.rvPopularMovie?.gone()
        mBinding?.rvTopRatedMovie?.gone()
        mBinding?.rvTrendingMovie?.gone()
        mBinding?.rvUpcomingMovie?.gone()
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
        showToast(requireContext(), message)
        mBinding?.swipeRefresh?.swipeGone()
    }

    private fun onSuccessPosterMovie(list: List<Poster>?) {
        val poster = list?.firstOrNull()
        val imageSize = getString(R.string.w780)
        val imageUrl = "$BASE_URL_IMAGE$imageSize/${poster?.filePath}"
        mBinding?.ivPosterHome?.setImage(imageUrl)
        mBinding?.ivPosterHome?.visible()
    }

    private fun onSuccessMoviePopular(listPopularMovie: List<MovieResult>?) {
        mBinding?.rvPopularMovie?.visible()
        setDataMoviePopular(listPopularMovie)
    }

    private fun onSuccessMovieTopRating(listTopRatedMovie: List<MovieResult>?) {
        mBinding?.rvTopRatedMovie?.visible()
        setDataTopRatingMovie(listTopRatedMovie)
    }

    private fun onSuccessTrendingMovie(listTrendingMovie: List<MovieResult>?) {
        mBinding?.rvTrendingMovie?.visible()
        setDataMovieTrending(listTrendingMovie)
    }

    private fun onSuccessDataMovieUpComing(listMovieUpcoming: List<MovieResult>?) {
        mBinding?.rvUpcomingMovie?.visible()
        setDataMovieUpComing(listMovieUpcoming)
    }


    /**
     * A Function set data movie into adapter*/

    private fun setDataMoviePopular(list: List<MovieResult>?) {
        mAdapterPopular.setData(list ?: emptyList())
    }

    private fun setDataTopRatingMovie(list: List<MovieResult>?) {
        mAdapterTopRatingMovie.setData(list ?: emptyList())
    }

    private fun setDataMovieTrending(list: List<MovieResult>?) {
        mAdapterTrendingMovie.setData(list ?: emptyList())
    }

    private fun setDataMovieUpComing(list: List<MovieResult>?) {
        mAdapterUpComingMovie.setData(list ?: emptyList())
    }
}