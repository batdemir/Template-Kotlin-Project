package com.batdemir.template.ui.view.github

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class GithubViewModel @Inject constructor(
    repository: GithubRepository
) : BaseViewModel() {
    val liveData: MutableLiveData<State> = MutableLiveData()

    init {
        repository.getUsers().asFlow().handle {
            liveData.value = State.OnDataResumed(it.map { item ->
                ActionItemModel(
                    id = item.id,
                    title = item.login,
                    subTitle = item.login,
                    iconRes = item.avatarUrl,
                    isEnabled = true,
                    navigateUrl = null,
                    isSelected = false
                )
            })
        }
    }

    sealed class State {
        data class OnDataResumed(val data: List<ActionItemModel>) : State()
    }
}