package com.batdemir.template.features.github

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.batdemir.core.data.remote.exception.Error
import com.batdemir.core.models.Resource
import com.batdemir.template.models.ui.ActionItemModel
import com.batdemir.template.other.Constant
import com.google.gson.Gson
import kotlinx.coroutines.delay
import retrofit2.HttpException

class GithubUserPagingRemoteDataSource(
    private val remoteDataSource: GithubUserRemoteDataSource,
    private val searchParams: GithubSearchParams
) : PagingSource<GithubLoadParams, ActionItemModel>() {
    override suspend fun load(params: LoadParams<GithubLoadParams>): LoadResult<GithubLoadParams, ActionItemModel> {
        return try {
            val key = params.key ?: GithubLoadParams(Constant.START_PAGE_INDEX)
            val response = remoteDataSource.getUsers(
                since = key.since,
                perPage = searchParams.perPage,
            )
            if (response.status == Resource.Status.ERROR) {
                throw Exception(response.throwable)
            }
            val prevKey = when (key.since) {
                Constant.START_PAGE_INDEX -> null
                else -> GithubLoadParams(key.since - 1)
            }
            val nextKey = when {
                response.data?.isNullOrEmpty() == true -> null
                else -> GithubLoadParams(response.data?.maxOf { x -> x.id } ?: 0L)
            }
            delay(Constant.DELAY)
            LoadResult.Page(
                data = response.data?.map { model ->
                    ActionItemModel(
                        id = model.id,
                        title = model.login,
                        subTitle = model.type,
                        iconRes = model.avatarUrl,
                        isEnabled = true,
                        navigateUrl = null,
                    )
                } ?: listOf(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                Throwable(
                    Gson().fromJson(
                        e.response()?.errorBody()?.charStream(),
                        Error::class.java
                    )
                )
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<GithubLoadParams, ActionItemModel>): GithubLoadParams {
        val anchorPosition = state.anchorPosition
        val prevKey = anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.since?.plus(1) }
        val nextKey = anchorPosition?.let { state.closestPageToPosition(it)?.nextKey?.since?.minus(1) }
        prevKey?.let {
            return GithubLoadParams(prevKey)
        }
        nextKey?.let {
            return GithubLoadParams(nextKey)
        }
        return GithubLoadParams(0)
    }
}

data class GithubLoadParams(
    var since: Long
)

data class GithubSearchParams(
    val perPage: Long? = null,
)
