package com.example.movieviews.external.constant

import com.example.movieviews.BuildConfig

//timeout network
const val networkConnectTimeout = 30L
const val networkWriteTimeout = 30L
const val networkReadTimeOut = 30L

const val API_KEY = BuildConfig.API_KEY
const val BASE_URL_IMAGE = BuildConfig.MOVIE_IMAGE_URL
const val language = "en-US"
const val collectionId = 10
const val MEDIA_TYPE = "movie"
const val TIME_WINDOW = "week"

const val EXTRA_MOVIE_ID = "extra_movie_id"
const val EXTRA_DATAIl_MOVIE = "extra_detail_movie"
const val EXTRA_TV_SHOW_MOVIE = "extra_tv_show_movie"