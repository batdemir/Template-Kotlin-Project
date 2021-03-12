package com.batdemir.template.ui.view.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class NotificationsViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseViewModel() {
    private val _models: MutableLiveData<List<ActionItemModel>> = MutableLiveData()
    val model: LiveData<List<ActionItemModel>> = _models

    fun getModels(forceRefresh: Boolean) {
        if (_models.value == null || forceRefresh)
            githubRepository.getUsers().sendRequest(
                onComplete = {
                    _models.postValue(it.map { item ->
                        ActionItemModel(
                            id = item.id,
                            title = item.login,
                            iconRes = item.avatarUrl,
                            subTitle = item.type,
                            isEnabled = true,
                            isSelected = false,
                            navigateUrl = null
                        )
                    })
                }
            )
    }
}