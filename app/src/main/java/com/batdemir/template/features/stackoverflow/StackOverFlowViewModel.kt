package com.batdemir.template.features.stackoverflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.batdemir.core.vm.BaseViewModel
import com.batdemir.template.models.ui.ActionItemModel
import com.batdemir.template.other.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StackOverFlowViewModel @Inject constructor(
    repository: StackOverFlowRepository
) : BaseViewModel() {
    val liveData: MutableLiveData<State> = MutableLiveData()

    init {
        viewModelScope.launch {
            repository.getUsersPaging(StackOverFlowSearchParams(pageSize = Constant.NETWORK_PAGE_SIZE.toLong()))
                .cachedIn(viewModelScope)
                .collectLatest { items ->
                    liveData.value = State.LoadItems(items)
                }
        }
    }

    sealed class State {
        data class LoadItems(val items: PagingData<ActionItemModel>) : State()
    }
}