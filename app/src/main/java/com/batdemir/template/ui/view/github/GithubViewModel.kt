package com.batdemir.template.ui.view.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class GithubViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseViewModel() {
    private val _models: MutableLiveData<List<ActionItemModel>> = MutableLiveData()
    val model: LiveData<List<ActionItemModel>> = _models

    fun getModels(forceRefresh: Boolean) {
        if (_models.value == null || forceRefresh)
            githubRepository.getUsers().asFlow().handle {
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
    }
}