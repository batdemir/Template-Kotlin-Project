package com.batdemir.template.core.view

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.batdemir.template.core.adapter.BasePagingAdapter
import com.batdemir.template.core.vm.BaseViewModel

interface BaseActionLoadState {
    fun <V : BaseViewModel, A : BasePagingAdapter<*, *>> setPagingAdapterLoadStateListener(
        viewModel: V,
        adapter: A
    ) = adapter.addLoadStateListener(getLoadStateListener(viewModel))

    fun <V : BaseViewModel, A : BasePagingAdapter<*, *>> removePagingAdapterLoadStateListener(
        viewModel: V,
        adapter: A
    ) = adapter.removeLoadStateListener(getLoadStateListener(viewModel))

    private fun <V : BaseViewModel> getLoadStateListener(viewModel: V): (CombinedLoadStates) -> Unit {
        return { loadState ->
            when {
                loadState.append is LoadState.Loading
                        || loadState.refresh is LoadState.Loading
                        || loadState.prepend is LoadState.Loading
                -> viewModel.baseLiveData.value = BaseViewModel.State.ShowLoading()
                else -> {
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    if (error == null) {
                        viewModel.baseLiveData.value = BaseViewModel.State.ShowContent()
                    } else {
                        viewModel.baseLiveData.value = BaseViewModel.State.ShowError(
                            throwable = error.error
                        )
                    }
                }
            }
        }
    }
}