package com.example.movieviews.presentation.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.R
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.FragmentHomeBinding
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.setupHorizontalLayoutManager
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelFactory
import com.example.movieviews.presentation.ui.fragment.home.viewmodel.HomeFragmentViewModelImpl

class HomeFragment : Fragment() {

    private var mBinding: FragmentHomeBinding? = null
    private lateinit var mFragmentViewModel: HomeFragmentViewModelImpl

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instances object,
     * if it is already it will be usable
     * */
    private val mAdapterPopular by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    startActivity(intent)
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity
                ) {

                }

            }
        }
    }

    private val mAdapterFreeWatch by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    startActivity(intent)
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity
                ) {

                }
            }
        }
    }

    private val mAdapterTrendingMovie by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    startActivity(intent)
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity
                ) {

                }
            }
        }
    }

    private val mAdapterUpComingMovie by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, data.id)
                    startActivity(intent)
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity,
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
        initView()
        onObserver()
    }


    private fun initView() {
        mFragmentViewModel = ViewModelProvider(
            requireActivity(),
            HomeFragmentViewModelFactory(
                InjectionModule.provideMovieRepository()
            )
        )[HomeFragmentViewModelImpl::class.java]
        setupView()
        initData()
        setupAdapterPopularMovie()
        setupAdapterFreeWatch()
        setupAdapterTrendingMovie()
        setupAdapterUpComingMovie()
    }

    private fun setupView() {
        //setup image in drawable
        mBinding?.ivPosterHome?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.banner
            )
        )
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
        mBinding?.tvSeeAllFreeWatch?.setOnClickListener {
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
        mFragmentViewModel.getMovie()
    }

    private fun setupAdapterPopularMovie() {
        mAdapterPopular.maxWidth = 160
        mBinding?.rvPopularMovie?.adapter = mAdapterPopular
        mBinding?.rvPopularMovie?.setupHorizontalLayoutManager()
    }

    private fun setupAdapterFreeWatch() {
        mAdapterFreeWatch.maxWidth = 160
        mBinding?.rvFreeWatch?.adapter = mAdapterFreeWatch
        mBinding?.rvFreeWatch?.setupHorizontalLayoutManager()
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
        mFragmentViewModel.state.observe(viewLifecycleOwner, { listMovie ->
            onDataChange(listMovie)
        })
    }

    private fun onDataChange(list: List<MovieEntity>) {
        //filter data based on condition
        val filterPopular = list.filter { it.isPopular }
        val filterFreeWatch = list.filter { it.isFreeWatch }
        val filterTrendingMovie = list.filter { it.isTrending }
        val filterUpComingMovie = list.filter { it.isUpComing }
        setDataMoviePopular(filterPopular)
        setDataMovieFreeWatch(filterFreeWatch)
        setDataMovieTrending(filterTrendingMovie)
        setDataMovieUpComing(filterUpComingMovie)
    }

    /**
     * A Function set data movie into adapter*/

    private fun setDataMoviePopular(list: List<MovieEntity>) {
        mAdapterPopular.setData(list)
    }

    private fun setDataMovieFreeWatch(list: List<MovieEntity>) {
        mAdapterFreeWatch.setData(list)
    }

    private fun setDataMovieTrending(list: List<MovieEntity>) {
        mAdapterTrendingMovie.setData(list)
    }

    private fun setDataMovieUpComing(list: List<MovieEntity>) {
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

    /*override fun onDestroy() {
        super.onDestroy()
        try {
            mProgressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}