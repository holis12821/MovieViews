package com.example.movieviews.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movieviews.data.local.LocalDb
import com.example.movieviews.data.local.RemoteKey
import com.example.movieviews.data.models.MovieResult
import com.example.movieviews.external.constant.API_KEY
import com.example.movieviews.external.constant.STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException

@ExperimentalPagingApi
class MovieRemoteMediator(
    private val remoteDataSource: RemoteDataSource,
    private val db: LocalDb,
    private val movieFlags: Boolean
) : RemoteMediator<Int, MovieResult>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieResult>
    ): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
             else -> pageKeyData as Int
        }

        try {
            val queryMap = HashMap<String, Any?>()
            queryMap["api_key"] = API_KEY
            queryMap["page"] = page
            queryMap["pageSize"] = state.config.pageSize
            val response = if (movieFlags) {
                remoteDataSource.getDiscoverMovie(queryMap = queryMap)
            } else {
                remoteDataSource.getDiscoverTvShow(queryMap)
            }
            val isEndOfList = response.results.isNullOrEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.getFavoriteMovieDao().deleteAll()
                    db.getKeysDao().deleteAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page -1
                val nextKey = if (isEndOfList) null else page + 1
                //mapping data movieResult to RemoteKey
                val keys = response.results?.map { movieResult ->
                    RemoteKey(movieResult.id ?: 0, prevKey = prevKey, nextKey = nextKey)
                }
                db.getKeysDao().insertAll(keys ?: emptyList())
                db.getFavoriteMovieDao().insertAll(response.results ?: emptyList())
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, MovieResult>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieResult>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                db.getKeysDao().remoteKeysMovieId(id = repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieResult>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movieResult -> db.getKeysDao().remoteKeysMovieId(id = movieResult.id ?: 0) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MovieResult>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movieResult -> db.getKeysDao().remoteKeysMovieId(id = movieResult.id ?: 0) }
    }

}