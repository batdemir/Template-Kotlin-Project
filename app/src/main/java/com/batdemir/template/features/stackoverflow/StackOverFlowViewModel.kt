package com.batdemir.template.features.stackoverflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.batdemir.core.vm.BaseViewModel
import com.batdemir.template.models.ui.ActionItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StackOverFlowViewModel @Inject constructor(
    repository: StackOverFlowRepository
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