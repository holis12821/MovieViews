package com.example.movieviews.di

import com.example.movieviews.BuildConfig
import com.example.movieviews.external.constant.networkConnectTimeout
import com.example.movieviews.external.constant.networkReadTimeOut
import com.example.movieviews.external.constant.networkWriteTimeout
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideConverterFactory() }
    single {
        val baseUrl = BuildConfig.BASE_URL
        provideRetrofitInstance(get(), get(), baseUrl = baseUrl)
    }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(networkConnectTimeout, TimeUnit.SECONDS)
        .writeTimeout(networkWriteTimeout, TimeUnit.SECONDS)
        .readTimeout(networkReadTimeOut, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofitInstance(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(gsonConverterFactory)
    .build()


