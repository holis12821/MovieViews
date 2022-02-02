package com.example.movieviews.test_koin
import com.example.movieviews.di.networkModule
import com.example.movieviews.di.repositoryModule
import com.example.movieviews.di.viewModelModule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinTestModule: KoinTest {
    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
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