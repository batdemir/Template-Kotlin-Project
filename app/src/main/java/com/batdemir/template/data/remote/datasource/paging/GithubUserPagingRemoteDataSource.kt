package com.batdemir.template.data.remote.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.di.module.remote.exception.Error
import com.google.gson.Gson
import retrofit2.HttpException

class GithubUserPagingRemoteDataSource(
    private val service: GithubService,
    private val searchParams: GithubSearchParams
) : PagingSource<GithubLoadParams, GithubUser>() {
    override suspend fun load(params: LoadParams<GithubLoadParams>): LoadResult<GithubLoadParams, GithubUser> {

        return try {
            val key = params.key ?: GithubLoadParams(Constant.START_PAGE_INDEX)
            val response = service.getUsersPaging(
                since = key.since,
                perPage = searchParams.perPage,
            )
            val prevKey = when (key.since) {
                Constant.START_PAGE_INDEX -> null
                else -> GithubLoadParams(key.since - 1)
            }
            val nextKey = when {
                response.isNullOrEmpty() -> null
                else -> GithubLoadParams(response.maxOf { x -> x.id })
            }
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(Throwable(Gson().fromJson(e.response()?.errorBody()?.charStream(), Error::class.java)))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<GithubLoadParams, GithubUser>): GithubLoadParams {
        return GithubLoadParams(0)
    }
}

data class GithubLoadParams(
    var since: Long
)

data class GithubSearchParams(
    val perPage: Long? = null,
)