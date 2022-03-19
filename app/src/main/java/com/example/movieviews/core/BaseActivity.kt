package com.example.movieviews.core

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.movieviews.external.utils.LogUtils
import com.example.movieviews.presentation.ui.custom.ProgressDialog

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var mBinding: B
    @LayoutRes
    protected abstract fun getResLayoutId(): Int
    private lateinit var progressDialog: ProgressDialog
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<B>(this, getResLayoutId())
            .apply { lifecycleOwner = this@BaseActivity }
        progressDialog = ProgressDialog(this)
        onActivityCreated(savedInstanceState = savedInstanceState)
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