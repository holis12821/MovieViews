package com.example.movieviews.presentation.ui.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.FragmentTvShowBinding
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.visible
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private var mBinding: FragmentTvShowBinding? = null
    private val mFragmentTvShowViewModel by viewModel<TvShowFragmentViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */

    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }

    private val mAdapterTvShowList by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {

                override fun onItemClickCallback(data: MovieResult) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_TV_SHOW_MOVIE, data.id)
                    intent.putExtra(EXTRA_DATAIl_MOVIE, false)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTvShowBinding.inflate(
            inflater,
            container, false
        )
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
        setupAdapterTvSHowList()
    }

    private fun initData() {
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
            is TvShowViewState.Loading -> onProgress()
            is TvShowViewState.Message -> onShowMessage(state.throwable.message.toString())
            is TvShowViewState.SuccessDiscoverTvShow -> onSuccessDiscoverTvShow(state.listTvSHowDiscover)
        }
    }

    private fun onInitState() {
        mBinding?.rvTvShow?.gone()
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

    private fun onSuccessDiscoverTvShow(listDiscoverTvShow: List<MovieResult>) {
        onHideProgress()
        mBinding?.rvTvShow?.visible()
        setDataTvShowList(listDiscoverTvShow)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataTvShowList(listDiscoverMovie: List<MovieResult>) {
        mAdapterTvShowList.setData(listDiscoverMovie)
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