package com.example.movieviews.data.remote

import com.example.movieviews.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {

    /**
     * This interface to handle service request and response from servers.
     * request asking to data such as JSON and response provided from servers
     * must be decode in order to be used and mapping to the object data.
     * this service applies the coroutine flow.
     * */

    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): BaseResponse<List<MovieResult>>

    @GET("3/collection/{collection_id}/images")
    suspend fun getCollectionImage(
        @Path("collection_id") collectionId: Int,
        @Query("api_key") api_key: String
    ): ImageCollection

    @GET("3/movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): BaseResponse<List<MovieResult>>

    @GET("3/movie/top_rated")
    suspend fun getMovieTopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): BaseResponse<List<MovieResult>>

    @GET("3/trending/{media_type}/{time_window}")
    suspend fun getTrendingMovie(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") api_key: String
    ): BaseResponse<List<MovieResult>>

    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): BaseResponse<List<MovieResult>>

    @GET("3/discover/tv")
    suspend fun getDiscoverTvShow(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): BaseResponse<List<MovieResult>>

    @GET("3/movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") movie_id: Int,
        @Query("api_key") api_key: String
    ): DetailMovieEntity

    @GET("3/tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") tv_id : Int,
        @Query("api_key") api_key: String
    ): DetailMovieEntity

    @GET("3/movie/{id}/credits")
    suspend fun getCreditsMovie(
        @Path("id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): CastMovieEntity
}