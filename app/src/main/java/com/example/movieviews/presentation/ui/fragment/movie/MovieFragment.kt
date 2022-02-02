package com.example.movieviews.presentation.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.databinding.FragmentMovieBinding
import com.example.movieviews.external.constant.EXTRA_DATAIl_MOVIE
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.visible
import com.example.movieviews.external.utils.EspressoIdlingResource
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var mBinding: FragmentMovieBinding? = null
    private val mFragmentMovieViewModel by viewModel<MovieFragmentViewModelImpl>()

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */

    private val mProgressDialog by lazy { ProgressDialog(requireContext()) }

    private val mAdapterMovieList by lazy {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMovieBinding.inflate(
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
        setupAdapterMovieList()
    }

    private fun initData() {
        mFragmentMovieViewModel.getListMovie()
    }

    private fun setupAdapterMovieList() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        mAdapterMovieList.maxWidth = 0
        mBinding?.rvMovie?.layoutManager = layoutManager
        mBinding?.rvMovie?.adapter = mAdapterMovieList
    }

    private fun onObserver() {
        mFragmentMovieViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: MovieViewState) {
        when (state) {
            is MovieViewState.Init -> onInitState()
            is MovieViewState.Loading -> onProgress()
            is MovieViewState.Message -> onShowMessage(state.throwable.message.toString())
            is MovieViewState.SuccessDiscoverMovie -> onSuccessDiscoverMovieList(state.listMovieDiscoverMovie)
        }
    }


    private fun onInitState() {
        mBinding?.rvMovie?.gone()
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

    private fun onSuccessDiscoverMovieList(list: List<MovieResult>) {
        onHideProgress()
        mBinding?.rvMovie?.visible()
        setDataMovieList(list)
    }

    /**
     * A Function set data movie into adapter
     * */
    private fun setDataMovieList(list: List<MovieResult>) {
        mAdapterMovieList.setData(list)
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