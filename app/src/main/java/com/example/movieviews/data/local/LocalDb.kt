package com.example.movieviews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieviews.data.local.dao.FavoriteMovieDao
import com.example.movieviews.data.models.DetailMovieEntity
import com.example.movieviews.data.models.MovieResult

/**
 * This Local Database using room.
 * class that becomes a class for database
 * room as well as made abstract class
 * to interact use DAO(Data Access Object) to get query SQL
 * */
@Database(entities = [MovieResult::class, DetailMovieEntity::class], version = 1, exportSchema = false)
abstract class LocalD : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}