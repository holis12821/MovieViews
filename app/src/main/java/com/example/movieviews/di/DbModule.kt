package com.example.movieviews.di

import android.content.Context
import androidx.room.Room
import com.example.movieviews.data.local.LocalDb
import com.example.movieviews.external.constant.DatabaseRoom
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    factory {
      get<LocalDb>().getFavoriteMovieDao()
    }

    single { provideDatabase(androidContext(), provideDatabaseName()) }
}

fun provideDatabaseName(): String = DatabaseRoom.FAVORITE_DATABASE_NAME

fun provideDatabase(context: Context, databaseName: String): LocalDb {
    return Room.databaseBuilder(context, LocalDb::class.java, databaseName)
        .build()
}