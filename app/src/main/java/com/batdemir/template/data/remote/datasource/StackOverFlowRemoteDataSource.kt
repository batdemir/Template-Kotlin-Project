package com.batdemir.template.data.remote.datasource

import com.batdemir.template.data.StackOverFlowOrderType
import com.batdemir.template.data.StackOverFlowSortType
import com.batdemir.template.data.entities.Resource
import com.batdemir.template.data.remote.service.StackOverFlowService
import com.batdemir.template.utils.execute
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StackOverFlowRemoteDataSource @Inject constructor(
    private val stackOverFlowService: StackOverFlowService
) {
    fun getUsers(
        page: Long? = null,
        pageSize: Long? = null,
        fromDate: Long? = null,
        toDate: Long? = null,
        orderType: StackOverFlowOrderType = StackOverFlowOrderType.ASC,
        min: Long? = null,
        max: Long? = null,
        sortType: StackOverFlowSortType = StackOverFlowSortType.NAME,
        inName: String? = null,
    ) = flow {
        emit(
            Resource.Success(
                stackOverFlowService.getUsers(
                    page = page,
                    pageSize = pageSize,
                    fromDate = fromDate,
                    toDate = toDate,
                    orderType = orderType.value,
                    min = min,
                    max = max,
                    sortType = sortType.value,
                    inName = inName,
                )
            )
        )
    }.execute()

    fun getUser(
        user: String = "users",
        page: Long? = null,
        pageSize: Long? = null,
        fromDate: Long? = null,
        toDate: Long? = null,
        orderType: StackOverFlowOrderType = StackOverFlowOrderType.ASC,
        min: Long? = null,
        max: Long? = null,
        sortType: StackOverFlowSortType = StackOverFlowSortType.NAME,
        inName: String? = null,
    ) = flow {
        emit(
            Resource.Success(
                stackOverFlowService.getUser(
                    user = user,
                    page = page,
                    pageSize = pageSize,
                    fromDate = fromDate,
                    toDate = toDate,
                    orderType = orderType.value,
                    min = min,
                    max = max,
                    sortType = sortType.value,
                    inName = inName,
                )
            )
        )
    }.execute()
}