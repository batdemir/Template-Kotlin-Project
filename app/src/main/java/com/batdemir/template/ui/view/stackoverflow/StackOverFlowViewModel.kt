package com.batdemir.template.ui.view.stackoverflow

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.remote.datasource.paging.StackOverFlowSearchParams
import com.batdemir.template.data.repository.StackOverFlowRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StackOverFlowViewModel @Inject constructor(
    private val stackOverFlowRepository: StackOverFlowRepository
) : BaseViewModel() {
    fun getModels(): Flow<PagingData<ActionItemModel>> = stackOverFlowRepository.getUsersPaging(
        StackOverFlowSearchParams(pageSize = Constant.NETWORK_PAGE_SIZE.toLong())
    ).cachedIn(viewModelScope)
}