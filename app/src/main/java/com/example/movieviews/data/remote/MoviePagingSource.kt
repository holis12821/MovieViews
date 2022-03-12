package com.example.movieviews.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.STARTING_PAGE_INDEX
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class MoviePagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val movieFlags: Boolean
): PagingSource<Int, MovieResult>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try {
            val page  = params.key ?: STARTING_PAGE_INDEX
            val queryMap = HashMap<String, Any?>()
            queryMap["api_key"] = API_KEY
            queryMap["page"] = page
            queryMap["pageSize"] = params.loadSize

            val response = if (movieFlags) {
                remoteDataSource.getDiscoverMovie(queryMap)
            } else {
                remoteDataSource.getDiscoverTvShow(queryMap)
            }
            val movies = response.results
            //delay before data is received
            delay(1000)
            LoadResult.Page(
                data = movies ?: emptyList(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (movies.isNullOrEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}