package com.example.movieviews.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.external.constant.DatabaseRoom

@Dao
interface MovieDbDao {

    @Query(DatabaseRoom.GET_ALL_FAVORITE)
    fun getAll(): PagingSource<Int, MovieResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieResult>)

    @Query(DatabaseRoom.FILTER_FAVORITE_WITH_ID)
    suspend fun isFavorite(id: Int): MovieResult

    @Query(DatabaseRoom.DELETE_FAVORITE_WITH_ID)
    suspend fun deleteFavoriteById(id: Int)

    @Query(DatabaseRoom.DELETE_ALL)
    suspend fun deleteAll()
}