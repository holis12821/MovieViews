package com.example.movieviews.presentation.ui.fragment.tvshow

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
import com.example.movieviews.databinding.FragmentTvShowBinding
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.visible
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelFactory
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowViewState

class TvShowFragment: Fragment() {
    private var mBinding: FragmentTvShowBinding? = null
    private lateinit var mFragmentTvShowViewModel: TvShowFragmentViewModelImpl
    private var loading: Boolean = false

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }

    private val mAdapterTvShowList by lazy {
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
            inflater, R.layout.fragment_tv_show,
            container, false
        )
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onObserver()
    }

    private fun initView() {
        mFragmentTvShowViewModel = ViewModelProvider(
            requireActivity(),
            TvShowFragmentViewModelFactory(
                InjectionModule.provideMovieRepository()
            ))[TvShowFragmentViewModelImpl::class.java]
        onInitState()
        initData()
        setupAdapterTvSHowList()
    }

    private fun initData() {
        mBinding?.viewModel = mFragmentTvShowViewModel
        mFragmentTvShowViewModel.getTvShowList()
    }

    private fun setupAdapterTvSHowList() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterTvShowList.maxWidth = 0
        mBinding?.rvTvShow?.layoutManager = layoutManager
        mBinding?.rvTvShow?.adapter = mAdapterTvShowList
    }

    private fun onObserver() {
        mFragmentTvShowViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: TvShowViewState) {
        when (state) {
            is TvShowViewState.Init -> onInitState()
            is TvShowViewState.Progress -> onProgress(state.isLoading)
            is TvShowViewState.ShowMessage -> onShowMessage(state.message)
            is TvShowViewState.ShowTvShow -> onSuccess(state.list)
        }
    }

    private fun onInitState() {
        mBinding?.rvTvShow?.gone()
    }

    private fun onProgress(loading: Boolean) {
        this.loading = loading
        if (loading) mProgressDialog.show()
        else mProgressDialog.dismiss()
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(requireContext(),
        message, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(list: List<MovieEntity>) {
        mBinding?.rvTvShow?.visible()
        val filterTvShowList = list.filter { it.isTvSHow }
        setDataTvShowList(filterTvShowList)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataTvShowList(list: List<MovieEntity>) {
        mAdapterTvShowList.setData(list)
    }

    /**
     * A function navigate to the TvSHow detail Fragment
     * */
    fun navigateTvShowDetail(movieEntity: MovieEntity) {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavigationView()
        }
        findNavController().navigate(TvShowFragmentDirections
            .actionTvShowToDetailTvShow(movieEntity))
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