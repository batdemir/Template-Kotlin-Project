package com.batdemir.template.data.remote.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.batdemir.template.data.Constant
import com.batdemir.template.data.StackOverFlowOrderType
import com.batdemir.template.data.StackOverFlowSortType
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.remote.service.StackOverFlowService
import com.batdemir.template.di.module.remote.exception.Error
import com.google.gson.Gson
import retrofit2.HttpException

class StackOverFlowPagingRemoteDataSource(
    private val service: StackOverFlowService,
    private val searchParams: StackOverFlowSearchParams
) : PagingSource<StackOverFlowLoadParams, StackOverFlowUser>() {
    override suspend fun load(params: LoadParams<StackOverFlowLoadParams>): LoadResult<StackOverFlowLoadParams, StackOverFlowUser> {
        return try {
            val key = params.key ?: StackOverFlowLoadParams(Constant.START_PAGE_INDEX)
            val response = service.getUsersPaging(
                page = key.page,
                pageSize = searchParams.pageSize,
                fromDate = searchParams.fromDate,
                toDate = searchParams.toDate,
                orderType = searchParams.orderType?.name ?: StackOverFlowOrderType.ASC.name,
                min = searchParams.min,
                max = searchParams.max,
                sortType = searchParams.sortType?.name ?: StackOverFlowSortType.NAME.value,
                inName = searchParams.inName,
                site = searchParams.site
            )
            val prevKey = when (key.page) {
                Constant.START_PAGE_INDEX -> null
                else -> StackOverFlowLoadParams(key.page - 1)
            }
            val nextKey = when {
                !response.hasMore -> null
                response.items.isNullOrEmpty() -> null
                else -> StackOverFlowLoadParams(key.page + 1)
            }
            LoadResult.Page(
                data = response.items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(Throwable(Gson().fromJson(e.response()?.errorBody()?.charStream(), Error::class.java)))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<StackOverFlowLoadParams, StackOverFlowUser>): StackOverFlowLoadParams {
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
    val inName: String? = null,
    val site: String = "stackoverflow"
)