package com.example.movieviews.presentation.ui.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.FragmentTvShowBinding
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.visible
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelFactory
import com.example.movieviews.presentation.ui.fragment.tvshow.viewmodel.TvShowFragmentViewModelImpl

class TvShowFragment : Fragment() {
    private var mBinding: FragmentTvShowBinding? = null
    private lateinit var mFragmentTvShowViewModel: TvShowFragmentViewModelImpl
//    private var loading: Boolean = false

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mAdapterTvShowList by lazy {
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
        initView()
        onObserver()
    }

    private fun initView() {
        mFragmentTvShowViewModel = ViewModelProvider(
            requireActivity(),
            TvShowFragmentViewModelFactory(
                InjectionModule.provideMovieRepository()
            )
        )[TvShowFragmentViewModelImpl::class.java]
        initData()
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
        mFragmentTvShowViewModel.state.observe(viewLifecycleOwner, { listMovie ->
            onDataSuccess(listMovie)
        })
    }

    private fun onDataSuccess(list: List<MovieEntity>) {
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