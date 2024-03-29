package com.batdemir.template.features.stackoverflow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.batdemir.core.data.remote.exception.Error
import com.batdemir.core.models.Resource
import com.batdemir.template.models.ui.ActionItemModel
import com.batdemir.template.other.Constant
import com.batdemir.template.other.StackOverFlowOrderType
import com.batdemir.template.other.StackOverFlowSortType
import com.google.gson.Gson
import kotlinx.coroutines.delay
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
            if (response.status == Resource.Status.ERROR) {
                throw Exception(response.throwable)
            }
            val prevKey = when (key.page) {
                Constant.START_PAGE_INDEX -> null
                else -> StackOverFlowLoadParams(key.page - 1)
            }
            val nextKey = when {
                response.data?.hasMore == false -> null
                response.data?.items.isNullOrEmpty() -> null
                else -> StackOverFlowLoadParams(key.page + 1)
            }
            delay(Constant.DELAY)
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

    override fun getRefreshKey(state: PagingState<StackOverFlowLoadParams, ActionItemModel>): StackOverFlowLoadParams {
        val anchorPosition = state.anchorPosition
        val prevKey = anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.page?.plus(1) }
        val nextKey = anchorPosition?.let { state.closestPageToPosition(it)?.nextKey?.page?.minus(1) }
        prevKey?.let {
            return StackOverFlowLoadParams(prevKey)
        }
        nextKey?.let {
            return StackOverFlowLoadParams(nextKey)
        }
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
