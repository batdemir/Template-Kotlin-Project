package com.batdemir.template.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.batdemir.template.data.Constant
import com.batdemir.template.data.StackOverFlowOrderType
import com.batdemir.template.data.StackOverFlowSortType
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.di.module.remote.exception.Error
import com.google.gson.Gson
import retrofit2.HttpException

class StackOverFlowPagingRemoteDataSource(
    private val remoteDataSource: StackOverFlowRemoteDataSource,
    private val searchParams: StackOverFlowSearchParams
) : PagingSource<StackOverFlowLoadParams, ActionItemModel>() {
    override suspend fun load(params: LoadParams<StackOverFlowLoadParams>): LoadResult<StackOverFlowLoadParams, ActionItemModel> {
        return try {
            val key = params.key ?: StackOverFlowLoadParams(Constant.START_PAGE_INDEX)
            val response = remoteDataSource.getUsers(
                page = key.page,
                pageSize = searchParams.pageSize,
                fromDate = searchParams.fromDate,
                toDate = searchParams.toDate,
                orderType = searchParams.orderType ?: StackOverFlowOrderType.ASC,
                min = searchParams.min,
                max = searchParams.max,
                sortType = searchParams.sortType ?: StackOverFlowSortType.NAME,
                inName = searchParams.inName
            )
            val prevKey = when (key.page) {
                Constant.START_PAGE_INDEX -> null
                else -> StackOverFlowLoadParams(key.page - 1)
            }
            val nextKey = when {
                response.data?.hasMore == false -> null
                response.data?.items.isNullOrEmpty() -> null
                else -> StackOverFlowLoadParams(key.page + 1)
            }
            LoadResult.Page(
                data = response.data?.items?.map { model ->
                    ActionItemModel(
                        id = model.id,
                        title = model.displayName,
                        subTitle = model.userType,
                        iconRes = model.profileImage,
                        isEnabled = true,
                        navigateUrl = null,
                    )
                } ?: listOf(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(Throwable(Gson().fromJson(e.response()?.errorBody()?.charStream(), Error::class.java)))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<StackOverFlowLoadParams, ActionItemModel>): StackOverFlowLoadParams {
        return StackOverFlowLoadParams(0)
    }
}

data class StackOverFlowLoadParams(
    var page: Long
)

data class StackOverFlowSearchParams(
    val pageSize: Long? = null,
    val fromDate: Long? = null,
    val toDate: Long? = null,
    val orderType: StackOverFlowOrderType? = null,
    val min: Long? = null,
    val max: Long? = null,
    val sortType: StackOverFlowSortType? = null,
    val inName: String? = null
)