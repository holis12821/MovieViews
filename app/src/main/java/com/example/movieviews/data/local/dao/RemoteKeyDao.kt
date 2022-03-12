package com.example.movieviews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieviews.data.local.RemoteKey
import com.example.movieviews.external.constant.DatabaseRoom

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query(DatabaseRoom.REMOTE_KEYS_BY_ID)
    suspend fun remoteKeysMovieId(id: Int): RemoteKey?

    @Query(DatabaseRoom.DELETE_ALL_REMOTE_KEYS)
    suspend fun deleteAll()

}