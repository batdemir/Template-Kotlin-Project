package com.batdemir.template.ui.view.stackoverflow

import com.batdemir.template.data.repository.StackOverFlowRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class StackOverFlowViewModel @Inject constructor(
    val repository: StackOverFlowRepository
) : BaseViewModel()