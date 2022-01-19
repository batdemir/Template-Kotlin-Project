package com.batdemir.template.ui.github

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.batdemir.template.core.vm.BaseViewModel
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    val repository: GithubRepository
) : BaseViewModel() {
    val liveData: MutableLiveData<State> = MutableLiveData()

    init {
        repository.getUsersRemoteAndLocal().asFlow().handle {
            val items = it.map { item ->
                ActionItemModel(
                    id = item.id,
                    title = item.login,
                    subTitle = item.name,
                    iconRes = item.avatarUrl,
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