package com.batdemir.template.ui.view.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.repository.StackOverFlowRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val stackOverFlowRepository: StackOverFlowRepository
) : BaseViewModel() {
    private val _models: MutableLiveData<List<ActionItemModel>> = MutableLiveData()
    val model: LiveData<List<ActionItemModel>> = _models

    fun getModels(forceRefresh: Boolean) {
        if (_models.value == null || forceRefresh)
            stackOverFlowRepository.getUsers().sendRequest(
                onComplete = {
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
            )
    }
}