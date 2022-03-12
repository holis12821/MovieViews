package com.example.movieviews.core

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.movieviews.di.dbModule
import com.example.movieviews.di.networkModule
import com.example.movieviews.di.repositoryModule
import com.example.movieviews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MovieViewApps : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Timber.plant(Timber.DebugTree())

        /**
         * Start koin with add module from dependency
         * */
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MovieViewApps)
            //module
            modules(
                dbModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}