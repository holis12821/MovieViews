package com.example.movieviews.test_koin

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.example.movieviews.di.networkModule
import com.example.movieviews.di.repositoryModule
import com.example.movieviews.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.kotlin.mock

class KoinTestModule : KoinTest {

    private var context: Application = mock()

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            androidContext(context)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }.checkModules()
    }
}