package com.example.movieviews.di

import com.example.movieviews.BuildConfig
import com.example.movieviews.external.constant.PING_INTERVAL
import com.example.movieviews.external.constant.networkConnectTimeout
import com.example.movieviews.external.constant.networkReadTimeOut
import com.example.movieviews.external.constant.networkWriteTimeout
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single {
        val baseUrl = BuildConfig.BASE_URL
        provideRetrofitInstance(get(), get(), baseUrl = baseUrl)
    }
    factory { provideConverterFactory() }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}

@Throws(IOException::class)
fun provideDefaultHttpClient(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        return@Interceptor chain.proceed(request = request)
    }
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .retryOnConnectionFailure(retryOnConnectionFailure = true)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(provideDefaultHttpClient())
        .pingInterval(PING_INTERVAL, TimeUnit.SECONDS)
        .connectTimeout(networkConnectTimeout, TimeUnit.SECONDS)
        .writeTimeout(networkWriteTimeout, TimeUnit.SECONDS)
        .readTimeout(networkReadTimeOut, TimeUnit.SECONDS)
        .build()
}

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofitInstance(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(gsonConverterFactory)
    .client(okHttpClient)
    .build()


