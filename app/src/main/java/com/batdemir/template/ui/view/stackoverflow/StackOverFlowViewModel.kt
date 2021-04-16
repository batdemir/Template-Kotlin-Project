package com.batdemir.template.ui.view.stackoverflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.StackOverFlowRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class StackOverFlowViewModel @Inject constructor(
    private val stackOverFlowRepository: StackOverFlowRepository
) : BaseViewModel() {
    private val _models: MutableLiveData<List<ActionItemModel>> = MutableLiveData()
    val model: LiveData<List<ActionItemModel>> = _models

    fun getModels(forceRefresh: Boolean) {
        if (_models.value == null || forceRefresh)
            stackOverFlowRepository.getUsers().asFlow().handle {
                _models.postValue(it.items.map { item ->
                    ActionItemModel(
                        id = item.userId,
                        title = item.displayName,
                        iconRes = item.profileImage,
                        subTitle = item.reputation.toString(),
                        isEnabled = true,
                        isSelected = false,
                        navigateUrl = null
                    )
                })
            }
    }
}