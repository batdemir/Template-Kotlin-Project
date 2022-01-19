package com.batdemir.template.ui.stackoverflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.batdemir.template.core.vm.BaseViewModel
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.StackOverFlowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StackOverFlowViewModel @Inject constructor(
    val repository: StackOverFlowRepository
) : BaseViewModel() {
    val liveData: MutableLiveData<State> = MutableLiveData()

    init {
        repository.getUsersRemoteAndLocal().asFlow().handle {
            val items = it.map { item ->
                ActionItemModel(
                    id = item.id,
                    title = item.displayName,
                    subTitle = item.link,
                    iconRes = item.profileImage,
                )
            }
            liveData.value = if (items.isNullOrEmpty()) State.Empty else State.Init(items)
        }
    }

    sealed class State {
        data class Init(val items: List<ActionItemModel>) : State()
        object Empty : State()
    }
}