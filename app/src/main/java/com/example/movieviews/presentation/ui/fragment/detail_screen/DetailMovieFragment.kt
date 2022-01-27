package com.example.movieviews.presentation.ui.fragment.detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movieviews.R
import com.example.movieviews.data.models.CastEntity
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.FragmentDetailMovieBinding
import com.example.movieviews.external.extension.*
import com.example.movieviews.external.utils.setImage
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.CastAdapterMovie
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.presentation.ui.fragment.detail_screen.viewmodel.DetailMovieFragmentViewModelFactory
import com.example.movieviews.presentation.ui.fragment.detail_screen.viewmodel.DetailMovieFragmentViewModelImpl
import com.example.movieviews.presentation.ui.fragment.detail_screen.viewmodel.DetailMovieViewState

class DetailMovieFragment : Fragment() {

    private var mBinding: FragmentDetailMovieBinding? = null
    private lateinit var mFragmentDetailMovieViewModel: DetailMovieFragmentViewModelImpl

    //get data from previous fragment
    private val args: DetailMovieFragmentArgs by navArgs()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailMovieBinding.inflate(inflater,
        container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onObserver()
    }

    private fun initView() {
        mFragmentDetailMovieViewModel = ViewModelProvider(
            requireActivity(),
            DetailMovieFragmentViewModelFactory(
                InjectionModule.provideMovieRepository()
            )
        )[DetailMovieFragmentViewModelImpl::class.java]
        onInitState()
        initData()
        setupAdapterCastMovie()
    }

    private fun initData() {
        mFragmentDetailMovieViewModel.id = args.movieEntity.id
        mFragmentDetailMovieViewModel.getDetailMovie()
    }

    private fun setupAdapterCastMovie() {
        mAdapterCastMovie.maxWidth = 125
        mBinding?.rvBilledCast?.adapter = mAdapterCastMovie
        mBinding?.rvBilledCast?.setupHorizontalLayoutManager()
    }

    private fun onObserver() {
        mFragmentDetailMovieViewModel.state.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: DetailMovieViewState) {
        when (state) {
            is DetailMovieViewState.Init -> onInitState()
            is DetailMovieViewState.Progress -> onProgress(state.isLoading)
            is DetailMovieViewState.ShowMessage -> onShowMessage(state.message)
            is DetailMovieViewState.ShowDetailMovie -> onShowDetailMovie(state.detailMovieEntity)
            is DetailMovieViewState.ShowCastMovie -> onShowCastMovie(state.detailMovieEntity)
        }
    }

    private fun onInitState() {
        mBinding?.rvBilledCast?.gone()
    }

    private fun onProgress(loading: Boolean) {
//        if (loading) mProgressDialog.show()
//        else mProgressDialog.dismiss()
    }

    private fun onShowMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message, Toast.LENGTH_SHORT
        ).show()
    }

    private fun onShowDetailMovie(detailMovieEntity: MovieEntity?) {
        showDetailMovie(detailMovieEntity)
    }

    private fun showDetailMovie(detailMovieEntity: MovieEntity?) {
        mBinding?.apply {
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
            tvDescOverview.text = detailMovieEntity?.overview
            if (detailMovieEntity?.tagLine.isNullOrEmpty()) tvTagline.text = "-"
            else tvTagline.text = detailMovieEntity?.tagLine
        }
    }

    private fun onShowCastMovie(list: List<CastEntity>?) {
        if (list.isNullOrEmpty()) {
            mBinding?.rvBilledCast?.gone()
            mBinding?.tvTopBilledCast?.gone()
        } else {
            mBinding?.rvBilledCast?.visible()
            mBinding?.tvTopBilledCast?.visible()
            mBinding?.tvTopBilledCast?.text = getString(R.string.top_billed_cast)
            setDataCastMovie(list)
        }
    }

    /**
     * A Function set data cast movie into adapter
     * */
    private fun setDataCastMovie(list: List<CastEntity>) {
        mAdapterCastMovie.setData(list)
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