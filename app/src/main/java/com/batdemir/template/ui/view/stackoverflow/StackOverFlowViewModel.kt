package com.batdemir.template.ui.view.stackoverflow

import com.batdemir.template.data.repository.StackOverFlowRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class StackOverFlowViewModel @Inject constructor(
    val repository: StackOverFlowRepository
) : BaseViewModel()
    val repository: StackOverFlowRepository
) : BaseViewModel()
    repository: StackOverFlowRepository
) : BaseViewModel() {
    val liveData: MutableLiveData<State> = MutableLiveData()

    init {
        repository.getUsers().asFlow().handle {
            liveData.value = State.OnDataResumed(it.map { item ->
                ActionItemModel(
                    id = item.id,
                    title = item.displayName,
                    subTitle = item.displayName,
                    iconRes = item.profileImage,
                    isEnabled = true,
                    navigateUrl = null,
                    isSelected = false,
                )
            })
        }
    }

    sealed class State {
        data class OnDataResumed(val data: List<ActionItemModel>) : State()
    }
}