package com.example.movieviews.core

import android.content.IntentFilter
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.movieviews.R
import com.example.movieviews.external.extension.showToast
import com.example.movieviews.external.utils.LogUtils
import com.example.movieviews.external.utils.callback.NetworkChangeListener
import com.example.movieviews.presentation.ui.custom.ProgressDialog
import com.example.movieviews.receiver.NetworkChangeReceiver

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var mBinding: B

    //add Broadcast Receiver to handle network state
    private val networkChangeReceiver = NetworkChangeReceiver().apply {
        callBack = object : NetworkChangeListener {
            override fun onNetworkChanged() {
                showToast(
                    this@BaseActivity,
                    getString(R.string.not_connection)
                )
            }
        }
    }

    @LayoutRes
    protected abstract fun getResLayoutId(): Int
    private lateinit var progressDialog: ProgressDialog
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<B>(this, getResLayoutId())
            .apply { lifecycleOwner = this@BaseActivity }
        progressDialog = ProgressDialog(this)

        //register receiver
        registerReceiver(
            networkChangeReceiver,
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        )

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
            //unregister receiver
            unregisterReceiver(networkChangeReceiver)
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtils.info(e.message.toString())
        }
    }
}