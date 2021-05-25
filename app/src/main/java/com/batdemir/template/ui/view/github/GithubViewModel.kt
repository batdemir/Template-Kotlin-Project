package com.batdemir.template.ui.view.github

import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class GithubViewModel @Inject constructor(
    val repository: GithubRepository
) : BaseViewModel()