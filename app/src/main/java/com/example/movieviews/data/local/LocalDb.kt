package com.example.movieviews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieviews.data.local.dao.MovieDbDao
import com.example.movieviews.data.local.dao.RemoteKeyDao
import com.example.movieviews.data.models.MovieResult

/**
 * This Local Database using room.
 * class that becomes a class for database
 * room as well as made abstract class
 * to interact use DAO(Data Access Object) to get query SQL
 * */
@Database(entities = [MovieResult::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class LocalDb : RoomDatabase() {
    abstract fun getFavoriteMovieDao(): MovieDbDao
    abstract fun getKeysDao(): RemoteKeyDao
}