package com.batdemir.template.ui.view.stackoverflow

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import com.batdemir.template.R
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentStackOverFlowBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BasePagingAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.base.vm.BaseViewModel
import com.batdemir.template.ui.view.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StackOverFlowFragment :
    BaseFragment<FragmentStackOverFlowBinding, StackOverFlowViewModel>(R.layout.fragment_stack_over_flow) {
    private val adapter by lazy {
        BasePagingAdapter(
            layoutId = R.layout.item_action,
            bindListener = object : BindListener<StackOverFlowUser, ItemActionBinding> {
                override fun onBind(
                    holderBase: BaseViewHolder<ItemActionBinding>,
                    model: StackOverFlowUser,
                    position: Int
                ) {
                    holderBase.binding.model = ActionItemModel(
                        id = model.id,
                        title = model.displayName,
                        subTitle = model.userType,
                        iconRes = model.profileImage,
                        isEnabled = true,
                        navigateUrl = null,
                    )
                }
            },
            diffCallback = object : DiffUtil.ItemCallback<StackOverFlowUser>() {
                override fun areItemsTheSame(oldItem: StackOverFlowUser, newItem: StackOverFlowUser): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: StackOverFlowUser, newItem: StackOverFlowUser): Boolean {
                    return oldItem.id == newItem.id
                }
            }
        )
    }

    override fun inject() = (requireActivity() as MainActivity).stackOverFlowComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        getBinding().adapter = adapter
    }

    override fun setupData() {
        super.setupData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getModels().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun setupListener() {
        getBinding().rootFragmentStackOverFlow.setOnRefreshListener {
            getBinding().rootFragmentStackOverFlow.isRefreshing = false
        }
        adapter.addLoadStateListener { loadState ->
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