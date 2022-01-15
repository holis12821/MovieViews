package com.example.movieviews.presentation.ui.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.R
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.FragmentMovieBinding
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.visible
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelFactory
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieViewState

class MovieFragment : Fragment() {
    private var mBinding: FragmentMovieBinding? = null
    private lateinit var mFragmentMovieViewModel: MovieFragmentViewModelImpl
    private var loading: Boolean = false

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */

    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }

    private val mAdapterMovieList by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieEntity> {
                override fun onItemClickCallback(data: MovieEntity) {
                    navigateMovieDetail(movieEntity = data)
                }

                override fun onViewClickCallback(
                    view: View,
                    data: MovieEntity
                ) {
                    when (view.id) {
                        R.id.iv_poster -> navigateMovieDetail(data)
                        R.id.tv_title -> navigateMovieDetail(data)
                    }
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
            R.layout.fragment_movie, container, false
        )
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onObserver()
    }

    private fun initView() {
        mFragmentMovieViewModel = ViewModelProvider(
            requireActivity(),
            MovieFragmentViewModelFactory(
                InjectionModule.provideMovieRepository()
            )
        )[MovieFragmentViewModelImpl::class.java]
        onInitState()
        initData()
        setupAdapterMovieList()
    }

    private fun initData() {
        mBinding?.viewModel = mFragmentMovieViewModel
        mFragmentMovieViewModel.getListMovie()
    }

    private fun setupAdapterMovieList() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterMovieList.maxWidth = 0
        mBinding?.rvMovie?.layoutManager = layoutManager
        mBinding?.rvMovie?.adapter = mAdapterMovieList
//        setupPagination(layoutManager)
    }

    /*private fun setupPagination(layoutManager: GridLayoutManager) {
        mBinding?.rvMovie?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisiblePosition = layoutManager
                    .findLastCompletelyVisibleItemPosition()
                //condition if data has been last position, hit API
                if (!loading && lastVisiblePosition ==
                    mAdapterMovieList.list.size -1) {

                }
            }
        })
    }*/

    private fun onObserver() {
        mFragmentMovieViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: MovieViewState) {
        when (state) {
            is MovieViewState.Init -> onInitState()
            is MovieViewState.Progress -> onProgress(state.isLoading)
            is MovieViewState.ShowMessage -> onShowMessage(state.message)
            is MovieViewState.ShowMovie -> onSuccess(state.list)
        }
    }

    private fun onInitState() {
        mBinding?.rvMovie?.gone()
    }

    private fun onProgress(loading: Boolean) {
        this.loading = loading
        if (loading) mProgressDialog.show()
        else mProgressDialog.dismiss()
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message, Toast.LENGTH_SHORT
        ).show()
    }

    private fun onSuccess(list: List<MovieEntity>) {
        mBinding?.rvMovie?.visible()
        val filterMovieNotUpComing = list.filter { !it.isUpComing && !it.isTvSHow }
        setDataMovieList(filterMovieNotUpComing)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataMovieList(list: List<MovieEntity>) {
        mAdapterMovieList.setData(list)
    }

    /**
     * A function navigate to the Movie detail Fragment
     * */
    fun navigateMovieDetail(movieEntity: MovieEntity) {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavigationView()
        }
        findNavController().navigate(
            MovieFragmentDirections
                .actionMovieToDetailMovie(movieEntity)
        )
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