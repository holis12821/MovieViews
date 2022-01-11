package com.example.movieviews.presentation.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieviews.R
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.FragmentHomeBinding
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.home.viewmoodel.FragmentHomeViewModelFactory
import com.example.movieviews.presentation.ui.fragment.home.viewmoodel.FragmentHomeViewModelImpl
import com.example.movieviews.presentation.ui.fragment.home.viewmoodel.HomeViewState
import com.example.movieviews.utils.gone
import com.example.movieviews.utils.setupHorizontalLayoutManager
import com.example.movieviews.utils.visible

class HomeFragment : Fragment() {

    private var mBinding: FragmentHomeBinding? = null
    private lateinit var mViewModel: FragmentHomeViewModelImpl

    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }
    
    private val mAdapterPopular by lazy {
       MovieAdapter().apply {
           listener = object : AdapterClickListener<MovieEntity> {
               override fun onItemClickCallback(data: MovieEntity, fragment: Fragment) {

               }

               override fun onViewClickCallback(view: View, data: MovieEntity, fragment: Fragment) {

               }

           }
       }
    }

    private val mAdapterFreeWatch by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity, fragment: Fragment) {

                }

                override fun onViewClickCallback(view: View, data: MovieEntity, fragment: Fragment) {

                }

            }
        }
    }

    private val mAdapterTrending by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity, fragment: Fragment) {

                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity,
                    fragment: Fragment
                ) {

                }

            }
        }
    }

    private val mAdapterUpComingMovie by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity, fragment: Fragment) {

                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity,
                    fragment: Fragment
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
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onObserver()
    }


    private fun initView() {
        /*
        * Lazy initialization is used to initialize objects when needed.
        * This method only once instances the object,
        * if it is already it will be usable*/
        val repository by lazy {
            InjectionModule.provideMovieRepository()
        }
        mViewModel = ViewModelProvider(
            requireActivity(),
            FragmentHomeViewModelFactory(repository)
        )[FragmentHomeViewModelImpl::class.java]
        //setup image in drawable
        mBinding?.ivPoster?.setImageDrawable(
            ContextCompat.getDrawable(requireContext(),
                R.drawable.banner))
        mBinding?.ivLogo?.setImageDrawable(
            ContextCompat.getDrawable(requireContext(),
            R.drawable.ic_tmdb_logo)
        )
        mBinding?.tvMovieDb?.text = resources.getString(R.string.app_name)
        onInitState()
        initData()
        setupAdapterPopularMovie()
        setupAdapterFreeWatch()
        setupAdapterTrendingMovie()
        setupAdapterUpComingMovie()
    }

    private fun initData() {
        mViewModel.getMovie()
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
        mAdapterTrending.maxWidth = 160
        mBinding?.rvTrendingMovie?.adapter = mAdapterTrending
        mBinding?.rvTrendingMovie?.setupHorizontalLayoutManager()
    }

    private fun setupAdapterUpComingMovie() {
        mAdapterUpComingMovie.maxWidth = 160
        mBinding?.rvUpcomingMovie?.adapter = mAdapterUpComingMovie
        mBinding?.rvUpcomingMovie?.setupHorizontalLayoutManager()
    }

    private fun onObserver() {
        mViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: HomeViewState) {
        when (state) {
            is HomeViewState.Init -> onInitState()
            is HomeViewState.Progress -> onProgress(state.isLoading)
            is  HomeViewState.ShowMessage -> onShowMessage(state.message)
            is HomeViewState.ShowMovie -> onSuccess(state.list)
        }
    }

    private fun onInitState() {
        mBinding?.rvPopularMovie?.gone()
        mBinding?.rvFreeWatch?.gone()
        mBinding?.rvTrendingMovie?.gone()
        mBinding?.rvUpcomingMovie?.gone()
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            mProgressDialog.show()
        } else {
            mProgressDialog.dismiss()
        }
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(requireContext(),
            message, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(list: List<MovieEntity>) {
        mBinding?.rvPopularMovie?.visible()
        mBinding?.rvFreeWatch?.visible()
        mBinding?.rvTrendingMovie?.visible()
        mBinding?.rvUpcomingMovie?.visible()
        //filter data based on condition
        val filterPopular = list.filter { it.isPopular}
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
        mAdapterTrending.setData(list)
    }

    private fun setDataMovieUpComing(list: List<MovieEntity>) {
        mAdapterUpComingMovie.setData(list)
    }

    /**
     * A function to navigate to the Movie detail Fragment
     * */
    fun movieDetail(movieEntity: MovieEntity) {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavigationView()
        }
        findNavController().navigate(HomeFragmentDirections
            .actionHomeToDetailMovie(movieEntity))
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