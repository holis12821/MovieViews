package com.example.movieviews.presentation.ui.fragment.tvshow

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.R
import com.example.movieviews.core.BaseFragment
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.FragmentTvShowBinding
import com.example.movieviews.external.constant.EXTRA_TV_SHOW_MOVIE
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.showToast
import com.example.movieviews.external.extension.visible
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : BaseFragment<FragmentTvShowBinding>() {

    override fun getResLayoutId(): Int = R.layout.fragment_tv_show

    private val mFragmentTvShowViewModel by viewModel<TvShowFragmentViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mAdapterTvShowList by lazy {
        MovieAdapter().apply {
            listener = object : AdapterClickListener<MovieResult> {

                override fun onItemClickCallback(data: MovieResult) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_TV_SHOW_MOVIE, data)
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

    override fun onViewCreated() {
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
            is TvShowViewState.HideLoading -> onHideProgress()
            is TvShowViewState.Message -> onShowMessage(state.throwable.message.toString())
            is TvShowViewState.SuccessDiscoverTvShow -> onSuccessDiscoverTvShow(state.listTvSHowDiscover)
        }
    }

    private fun onInitState() {
        mBinding?.rvTvShow?.gone()
    }

    private fun onProgress() {
        showDialogProgress()
    }

    private fun onHideProgress() {
        hideProgress()
    }

    private fun onShowMessage(message: String) {
        showToast(requireContext(), message = message)
    }

    private fun onSuccessDiscoverTvShow(listDiscoverTvShow: List<MovieResult>?) {
        if (listDiscoverTvShow.isNullOrEmpty()) {
            mBinding?.rvTvShow?.gone()
        } else {
            mBinding?.rvTvShow?.visible()
            setDataTvShowList(listDiscoverTvShow)
        }
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataTvShowList(listDiscoverMovie: List<MovieResult>) {
        mAdapterTvShowList.setData(listDiscoverMovie)
    }
}