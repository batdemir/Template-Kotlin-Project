package com.batdemir.template.features.stackoverflow

import com.batdemir.core.data.remote.BaseDataSource
import com.batdemir.template.other.StackOverFlowOrderType
import com.batdemir.template.other.StackOverFlowSortType
import javax.inject.Inject

class StackOverFlowRemoteDataSource @Inject constructor(
    private val stackOverFlowService: StackOverFlowService
) : BaseDataSource() {
    suspend fun getUsers(
        page: Long? = null,
        pageSize: Long? = null,
        fromDate: Long? = null,
        toDate: Long? = null,
        orderType: StackOverFlowOrderType = StackOverFlowOrderType.ASC,
        min: Long? = null,
        max: Long? = null,
        sortType: StackOverFlowSortType = StackOverFlowSortType.NAME,
        inName: String? = null,
    ) = getResult {
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
    }

    suspend fun getUser(
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
    ) = getResult {
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
    }
}
