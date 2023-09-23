package com.example.movieviews.presentation.ui.activity.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.data.models.Genre
import com.example.movieviews.databinding.ActivityFilterBinding
import com.example.movieviews.external.constant.EXTRA_FILTER
import com.example.movieviews.external.constant.EXTRA_GENRES
import com.example.movieviews.external.extension.gone
import com.example.movieviews.external.extension.setupVerticalLayoutManager
import com.example.movieviews.external.extension.toJson
import com.example.movieviews.external.extension.visible
import com.example.movieviews.presentation.ui.adapter.FilterGenresAdapter

class FilterActivity : BaseActivity<ActivityFilterBinding>() {

    private val filterGenreAdapter = FilterGenresAdapter(arrayListOf())
    override fun getResLayoutId(): Int = R.layout.activity_filter

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        initView()
    }

    private var filterData: List<Genre>? = null

    private fun initView() {
        setupShowToolbar()
        filterData = Genre.arrayFromData(intent.getStringExtra(EXTRA_GENRES))

        filterByGenres()

        mBinding.btnFilter.setOnClickListener { submitFilter() }
        mBinding.btnReset.setOnClickListener { resetFilter() }
    }

    private fun setupShowToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun filterByGenres() {
        if (filterData.isNullOrEmpty()) {
            mBinding.filterGenre.gone()
            mBinding.rvFilterGenres.gone()
        } else {
            mBinding.filterGenre.visible()
            mBinding.rvFilterGenres.visible()

            val filteredGenre = Genre.arrayFromData(intent.getStringExtra(EXTRA_FILTER))
            val listGenre = arrayListOf<Int>()
            filteredGenre?.forEach { genre ->
                genre.id?.let { listGenre.add(it) }
            }

            val categoriesList = arrayListOf<Genre>()
            filterData?.forEach { genre ->
                categoriesList.add(genre)
            }

            categoriesList.forEach { genre ->
                genre.isChecked = genre.id in listGenre
            }

            if (categoriesList.isEmpty()) {
                mBinding.filterGenre.gone()
                mBinding.rvFilterGenres.gone()
            }

            filterGenreAdapter.setData(categoriesList)
            mBinding.rvFilterGenres.setupVerticalLayoutManager()
            mBinding.rvFilterGenres.adapter = filterGenreAdapter

            mBinding.filterGenre.setCompoundDrawablesWithIntrinsicBounds(
                0,0, R.drawable.ic_arrow_up, 0
            )

            mBinding.filterGenre.setOnClickListener {
                if (mBinding.rvFilterGenres.visibility == View.VISIBLE) {
                    mBinding.filterGenre.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_arrow_down, 0
                    )
                    mBinding.rvFilterGenres.gone()
                } else {
                    mBinding.filterGenre.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_arrow_up, 0
                    )
                    mBinding.rvFilterGenres.visible()
                }
            }
        }
    }

    private fun submitFilter() {
        val intent = Intent()
        intent.putExtra(EXTRA_FILTER, filterGenreAdapter.getSelectValue().toJson())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun resetFilter() {
        filterGenreAdapter.list.forEach {
            it.isChecked = false
        }
        filterGenreAdapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}