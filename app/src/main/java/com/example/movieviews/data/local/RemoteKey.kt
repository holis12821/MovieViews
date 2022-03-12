package com.example.movieviews.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieviews.external.constant.DatabaseRoom

@Entity(tableName = DatabaseRoom.REMOTE_KEYS)
data class RemoteKey(
    @PrimaryKey
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
