package com.example.movieviews.presentation.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieviews.data.models.MovieEntity
import com.example.movieviews.databinding.FragmentMovieBinding
import com.example.movieviews.external.constant.EXTRA_MOVIE_ID
import com.example.movieviews.external.extension.visible
import com.example.movieviews.module.InjectionModule
import com.example.movieviews.presentation.ui.activity.MainActivity
import com.example.movieviews.presentation.ui.activity.detailmovie.DetailMovieActivity
import com.example.movieviews.presentation.ui.adapter.AdapterClickListener
import com.example.movieviews.presentation.ui.adapter.MovieAdapter
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelFactory
import com.example.movieviews.presentation.ui.fragment.movie.viewmodel.MovieFragmentViewModelImpl

class MovieFragment : Fragment() {
    private var mBinding: FragmentMovieBinding? = null
    private lateinit var mFragmentMovieViewModel: MovieFragmentViewModelImpl
//    private var loading: Boolean = false

    /**
     * Lazy initialization is used to initialize objects when needed.
     * This method only once invoke the instance object,
     * if it is already it will be usable
     * */
    private val mAdapterMovieList by lazy {
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
        mBinding = FragmentMovieBinding.inflate(
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
        mFragmentMovieViewModel = ViewModelProvider(
            requireActivity(),
            MovieFragmentViewModelFactory(
                InjectionModule.provideMovieRepository()
            )
        )[MovieFragmentViewModelImpl::class.java]
        initData()
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
        mFragmentMovieViewModel.state.observe(viewLifecycleOwner, { listMovie ->
            onDataChange(listMovie)
        })
    }

    private fun onDataChange(list: List<MovieEntity>) {
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