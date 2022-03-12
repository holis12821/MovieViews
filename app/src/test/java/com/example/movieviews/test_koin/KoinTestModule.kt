package com.example.movieviews.test_koin

import android.app.Application
import com.example.movieviews.di.dbModule
import com.example.movieviews.di.networkModule
import com.example.movieviews.di.repositoryModule
import com.example.movieviews.di.viewModelModule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.kotlin.mock

class KoinTestModule : KoinTest {

    private var context: Application = mock()

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            androidContext(context)
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }.checkModules()
    }
}