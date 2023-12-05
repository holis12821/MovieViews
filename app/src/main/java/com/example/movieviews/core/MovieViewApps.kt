package com.example.movieviews.core

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.paging.ExperimentalPagingApi
import com.example.movieviews.di.networkModule
import com.example.movieviews.di.repositoryModule
import com.example.movieviews.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MovieViewApps : MultiDexApplication() {
    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Timber.plant(Timber.DebugTree())

        /**
         * Start koin with add module from dependency
         * */
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MovieViewApps)
            //module
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}