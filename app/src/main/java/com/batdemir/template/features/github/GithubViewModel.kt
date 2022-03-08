package com.batdemir.template.features.github

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.batdemir.core.vm.BaseViewModel
import com.batdemir.template.models.ui.ActionItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    repository: GithubRepository
) : BaseViewModel() {
    val liveData: MutableLiveData<State> = MutableLiveData()

    init {
        viewModelScope.launch {
            repository.getUsersPaging(GithubSearchParams())
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