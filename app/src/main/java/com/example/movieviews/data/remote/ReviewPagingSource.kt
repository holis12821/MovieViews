package com.example.movieviews.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieviews.data.models.Review
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.STARTING_PAGE_INDEX
import com.example.movieviews.external.constant.language
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException

class ReviewPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val movieId: Int,
    private val currentPage: Int = STARTING_PAGE_INDEX
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val page = params.key ?: currentPage
            val queryMap = HashMap<String, Any?>()
            queryMap["movie_id"] = movieId
            queryMap["api_key"] = API_KEY
            queryMap["language"] = language
            queryMap["page"] = page
            queryMap["pageSize"] = params.loadSize

            val response =
                remoteDataSource.getReviewMoviePagingSource(movie_id = movieId, queryMap = queryMap)

            val movies = response.results
            //delay before data is received
            delay(500)
            LoadResult.Page(
                data = movies ?: emptyList(),
                prevKey = if (page == currentPage) null else page.minus(1),
                nextKey = if (movies.isNullOrEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}