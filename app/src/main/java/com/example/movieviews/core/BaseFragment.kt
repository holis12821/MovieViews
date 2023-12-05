package com.example.movieviews.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.movieviews.external.utils.LogUtils
import com.example.movieviews.presentation.ui.custom.ProgressDialog

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected var mBinding: B? = null

    @LayoutRes
    protected abstract fun getResLayoutId(): Int
    private lateinit var progressDialog: ProgressDialog
    protected abstract fun onViewCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<B>(
            inflater,
            getResLayoutId(), container, false
        )
            .apply { lifecycleOwner = this@BaseFragment }
        progressDialog = ProgressDialog(requireContext())
        onViewCreated()
        return mBinding?.root
    }

    protected fun handleError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error
        errorState?.let { loadStateError ->
            Toast.makeText(
                requireContext(),
                "${loadStateError.error}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    protected fun showDialogProgress() {
        try {
            progressDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtils.info(e.message.toString())
        }
    }

    protected fun hideProgress() {
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtils.info(e.message.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtils.info(e.message.toString())
        }
    }

}