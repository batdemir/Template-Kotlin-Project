package com.batdemir.core.core.view

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.batdemir.core.core.adapter.BasePagingAdapter
import com.batdemir.core.core.vm.BaseViewModel

interface BaseActionLoadState {
    fun <V : BaseViewModel, A : BasePagingAdapter<*, *>> addPagingAdapterLoadStateListener(
        viewModel: V,
        adapter: A
    ) = adapter.addLoadStateListener(getLoadStateListener(viewModel))

    fun <V : BaseViewModel, A : BasePagingAdapter<*, *>> removePagingAdapterLoadStateListener(
        viewModel: V,
        adapter: A
    ) = adapter.removeLoadStateListener(getLoadStateListener(viewModel))

    private fun <V : BaseViewModel> getLoadStateListener(viewModel: V): (CombinedLoadStates) -> Unit {
        return { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> viewModel.baseLiveData.value = BaseViewModel.State.ShowLoading()
                is LoadState.NotLoading -> viewModel.baseLiveData.value = BaseViewModel.State.Empty
                is LoadState.Error -> viewModel.baseLiveData.value = BaseViewModel.State.Empty
            }
        }
    }
}
