package com.example.movieviews.external.constant

import androidx.annotation.StringRes
import com.example.movieviews.BuildConfig
import com.example.movieviews.R

//timeout network
const val networkConnectTimeout = 30L
const val networkWriteTimeout = 30L
const val networkReadTimeOut = 30L
const val PING_INTERVAL = 30L

const val API_KEY = BuildConfig.API_KEY
const val BASE_URL_IMAGE = BuildConfig.MOVIE_IMAGE_URL
const val language = "en-US"
const val collectionId = 10
const val MEDIA_TYPE = "movie"
const val TIME_WINDOW = "week"
const val STARTING_PAGE_INDEX = 1
const val PAGE_SIZE = 6

const val EXTRA_MOVIE_ID = "extra_movie_id"
const val EXTRA_DATAIl_MOVIE = "extra_detail_movie"
const val EXTRA_TV_SHOW_MOVIE = "extra_tv_show_movie"

@StringRes
val TAB_TITLES_FRAGMENT = intArrayOf(
    R.string.movie,
    R.string.tvShow
)

object DatabaseRoom {
    const val MOVIE_TABLE_NAME = "tb_movie"
    const val REMOTE_KEYS = "tb_remote_keys"
    //query movie
    const val GET_ALL_FAVORITE = "SELECT * FROM tb_movie"
    const val FILTER_FAVORITE_WITH_ID = "SELECT * FROM tb_movie WHERE id =:id LIMIT 1"
    const val DELETE_FAVORITE_WITH_ID = "DELETE FROM tb_movie WHERE id = :id"
    const val DELETE_ALL = "DELETE FROM tb_movie"
    const val REMOTE_KEYS_BY_ID = "SELECT * FROM tb_remote_keys WHERE id = :id"
    const val DELETE_ALL_REMOTE_KEYS = "DELETE FROM tb_remote_keys"
    const val FAVORITE_DATABASE_NAME = "favorite.db"
}
