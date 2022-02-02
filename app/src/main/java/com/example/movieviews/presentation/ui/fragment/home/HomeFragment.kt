package com.example.movieviews.presentation.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.movieviews.R
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.data.models.Poster
import com.example.movieviews.databinding.FragmentHomeBinding
import com.example.movieviews.external.constant.BASE_URL_IMAGE
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.setImage
import com.example.movieviews.external.extension.setupHorizontalLayoutManager
import com.example.movieviews.external.extension.visible
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var mBinding: FragmentHomeBinding? = null
    private val mFragmentViewModel by viewModel<HomeFragmentViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instances object,
     * if it is already it will be usable
     * */

    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }

    private val mAdapterPopular by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {
                override fun onItemClickCallback(data: MovieResult) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    intent.putExtra(EXTRA_DATAIl_MOVIE, true)
                    startActivity(intent)
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
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    intent.putExtra(EXTRA_DATAIl_MOVIE, true)
                    startActivity(intent)
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
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    intent.putExtra(EXTRA_DATAIl_MOVIE, true)
                    startActivity(intent)
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
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    intent.putExtra(EXTRA_DATAIl_MOVIE, true)
                    startActivity(intent)
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieResult,
                ) {

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        mBinding?.ivLogo?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_tmdb_logo
            )
        )
        mBinding?.tvMovieDb?.text = getString(R.string.app_name)
        mBinding?.tvSeeAllPopular?.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.coming_soon),
                Toast.LENGTH_SHORT
            ).show()
        }
        mBinding?.tvSeeAllTopRatedMovie?.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.coming_soon),
                Toast.LENGTH_SHORT
            ).show()
        }
        mBinding?.tvSeeAllTrending?.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.coming_soon),
                Toast.LENGTH_SHORT
            ).show()
        }
        mBinding?.tvSeeAllUpcomingMovie?.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.coming_soon),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initData() {
        mFragmentViewModel.getPopularMovie()
        mFragmentViewModel.getCollectionImage()
        mFragmentViewModel.getMovieUpcoming()
        mFragmentViewModel.getMovieTopRated()
        mFragmentViewModel.getTrendingMovie()
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
        mProgressDialog.show()
    }

    private fun onHideProgress() {
        mProgressDialog.dismiss()
    }

    private fun onShowMessage(message: String) {
        onHideProgress()
        Toast.makeText(
            requireActivity(),
            message, Toast.LENGTH_SHORT
        ).show()
    }

    private fun onSuccessPosterMovie(list: List<Poster>) {
        onHideProgress()
        val poster = list.firstOrNull()
        val imageSize = getString(R.string.w780)
        val imageUrl = "$BASE_URL_IMAGE$imageSize/${poster?.filePath}"
        mBinding?.ivPosterHome?.setImage(imageUrl)
    }

    private fun onSuccessMoviePopular(listPopularMovie: List<MovieResult>) {
        onHideProgress()
        mBinding?.rvPopularMovie?.visible()
        setDataMoviePopular(listPopularMovie)
    }

    private fun onSuccessMovieTopRating(listTopRatedMovie: List<MovieResult>) {
        onHideProgress()
        mBinding?.rvTopRatedMovie?.visible()
        setDataTopRatingMovie(listTopRatedMovie)
    }

    private fun onSuccessTrendingMovie(listTrendingMovie: List<MovieResult>) {
        onHideProgress()
        mBinding?.rvTrendingMovie?.visible()
        setDataMovieTrending(listTrendingMovie)
    }

    private fun onSuccessDataMovieUpComing(listMovieUpcoming: List<MovieResult>) {
        onHideProgress()
        mBinding?.rvUpcomingMovie?.visible()
        setDataMovieUpComing(listMovieUpcoming)
    }


    /**
     * A Function set data movie into adapter*/

    private fun setDataMoviePopular(list: List<MovieResult>) {
        mAdapterPopular.setData(list)
    }

    private fun setDataTopRatingMovie(list: List<MovieResult>) {
        mAdapterTopRatingMovie.setData(list)
    }

    private fun setDataMovieTrending(list: List<MovieResult>) {
        mAdapterTrendingMovie.setData(list)
    }

    private fun setDataMovieUpComing(list: List<MovieResult>) {
        mAdapterUpComingMovie.setData(list)
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavigationView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
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