package com.batdemir.template.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.Resource
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.di.module.remote.exception.Error
import com.google.gson.Gson
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
                else -> GithubLoadParams(response.data.maxOf { x -> x.id })
            }
            LoadResult.Page(
                data = response.data.map { model ->
                    ActionItemModel(
                        id = model.id,
                        title = model.login,
                        subTitle = model.type,
                        iconRes = model.avatarUrl,
                        isEnabled = true,
                        navigateUrl = null,
                    )
                },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(Throwable(Gson().fromJson(e.response()?.errorBody()?.charStream(), Error::class.java)))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<GithubLoadParams, ActionItemModel>): GithubLoadParams {
        return GithubLoadParams(0)
    }
}

data class GithubLoadParams(
    var since: Long
)

data class GithubSearchParams(
    val perPage: Long? = null,
)