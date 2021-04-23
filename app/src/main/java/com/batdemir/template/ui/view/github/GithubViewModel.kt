package com.batdemir.template.ui.view.github

import androidx.paging.PagingData
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.remote.datasource.paging.GithubSearchParams
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.ui.base.vm.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseViewModel() {
    fun getModels(): Flow<PagingData<GithubUser>> = githubRepository.getUsersPaging(
        GithubSearchParams(perPage = Constant.NETWORK_PAGE_SIZE.toLong())
    )
}