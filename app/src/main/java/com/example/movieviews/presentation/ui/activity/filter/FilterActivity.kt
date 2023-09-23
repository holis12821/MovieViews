package com.example.movieviews.presentation.ui.activity.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.data.models.Genre
import com.example.movieviews.data.models.Sort
import com.example.movieviews.databinding.ActivityFilterBinding
import com.example.movieviews.external.constant.EXTRA_FILTER
import com.example.movieviews.external.constant.EXTRA_GENRES
import com.example.movieviews.external.constant.EXTRA_SELECTED_SORT
import com.example.movieviews.external.constant.EXTRA_SORT
import com.example.movieviews.external.constant.TYPE
import com.example.movieviews.external.constant.TYPE_FILTER
import com.example.movieviews.external.extension.convertDpToPixel
import com.example.movieviews.external.extension.getRadioButtonColor
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
    private var sortData: List<Sort>? = null

    private var selectedSort: String? = null
    private var type: String? = null

    private fun initView() {
        setupShowToolbar()
        filterData = Genre.arrayFromData(intent.getStringExtra(EXTRA_GENRES))
        sortData = Sort.arrayFromData(intent.getStringExtra(EXTRA_SORT))
        selectedSort = intent.getStringExtra(EXTRA_SELECTED_SORT)
        type = intent.getStringExtra(TYPE)

        filterByGenres()
        sorting()

        mBinding.btnPrimary.text =
            if (type == TYPE_FILTER) getString(R.string.btn_filter) else getString(R.string.btn_sort)

        mBinding.btnPrimary.setOnClickListener {
            if (type == TYPE_FILTER) {
                submitFilter()
            } else {
                submitSort()
            }
        }
        mBinding.btnReset.isVisible = type == TYPE_FILTER
        mBinding.btnReset.setOnClickListener { resetFilter() }
    }

    private fun submitSort() {
        val checkId = mBinding.optionSortBy.checkedRadioButtonId
        val selectedSort = findViewById<RadioButton>(checkId)
        sortData?.forEach {
            if (it.title == selectedSort?.text) {
                val intent = Intent()
                intent.putExtra(EXTRA_SELECTED_SORT, it.value)
                setResult(Activity.RESULT_OK, intent)
                return@forEach
            }
        }
        finish()
    }

    private fun setupShowToolbar() {
        supportActionBar?.title = getString(R.string.title_nav_bar_filter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun sorting() {
        if (sortData.isNullOrEmpty()) {
            mBinding.optionSortBy.gone()
        } else {
            mBinding.optionSortBy.visible()

            mBinding.optionSortBy.removeAllViews()
            sortData?.forEach { sorts ->
                val option = AppCompatRadioButton(this)
                option.text = sorts.title
                option.buttonTintList = option.getRadioButtonColor()
                option.setTextColor(ContextCompat.getColor(this, R.color.primary_light))
                val layoutParams = RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, convertDpToPixel(this, 8), 0, 0)
                option.layoutParams = layoutParams
                mBinding.optionSortBy.addView(option)
                if (sorts.value == selectedSort) {
                    option.performClick()
                }
            }
        }
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
                0, 0, R.drawable.ic_arrow_up, 0
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