package com.example.movieviews.presentation.ui.activity.videoview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.movieviews.R
import com.example.movieviews.core.BaseActivity
import com.example.movieviews.databinding.ActivityVideoViewBinding
import com.example.movieviews.external.constant.EXTRA_KEY_VIDEO
import com.example.movieviews.external.constant.EXTRA_URL
import com.example.movieviews.external.constant.TYPE_VIDEO
import com.example.movieviews.external.utils.LogUtils
import timber.log.Timber

class VideoViewActivity : BaseActivity<ActivityVideoViewBinding>() {
    override fun getResLayoutId(): Int = R.layout.activity_video_view

    private var keyYoutube: String? = null
    private var keyType: String? = null
    private var url: String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        keyYoutube = intent.getStringExtra(EXTRA_KEY_VIDEO)
        keyType = intent.getStringExtra(TYPE_VIDEO)
        url = intent.getStringExtra(EXTRA_URL)
        setupShowToolbar()
        showVideo()
        setupWebView()
    }

    private fun setupShowToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        mBinding.videosWebView.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    showVideo()
                }

                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url?.contains("https://www.youtube.com/watch") == true) {
                        try {
                            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$url?v=$keyYoutube"))
                            startActivity(webIntent)
                            return true
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    return super.shouldOverrideUrlLoading(view, url)
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                }

                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    return super.onConsoleMessage(consoleMessage)
                }
            }

            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.allowFileAccess = true
            settings.domStorageEnabled = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            settings.pluginState = WebSettings.PluginState.ON
            settings.mediaPlaybackRequiresUserGesture = false

            val videoUrl = "${this@VideoViewActivity.url}?v=${keyYoutube}?autoplay=1&fs=0"
            loadUrl(videoUrl)
            LogUtils.info(videoUrl)
        }
    }

    private fun showVideo() {
        if (keyType == TYPE_VIDEO) {
           Handler(Looper.getMainLooper()).postDelayed({
               val scriptTag = if (url?.contains("https://www.youtube.com/watch") == true)"document.getElementsByClassName('ytp-play-button')[0].click()"
               else "document.getElementsByTagName('video')[0].play()"
               mBinding.videosWebView.evaluateJavascript("(function() { return ($scriptTag); })();") {result ->
                   Timber.tag("playScript").e(result)
               }
           }, 500)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}