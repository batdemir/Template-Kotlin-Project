package com.batdemir.template.features.stackoverflow

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.batdemir.core.adapter.*
import com.batdemir.core.extensions.observe
import com.batdemir.core.view.BaseActionLoadState
import com.batdemir.core.view.BaseFragment
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentStackOverFlowBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.models.ui.ActionItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StackOverFlowFragment :
    BaseFragment<FragmentStackOverFlowBinding, StackOverFlowViewModel>(R.layout.fragment_stack_over_flow), BaseActionLoadState {
    private val viewModel: StackOverFlowViewModel by viewModels()
    private val adapter by lazy {
        BasePagingAdapter(
            layoutId = R.layout.item_action,
            bindListener = object : BindListener<ActionItemModel, ItemActionBinding> {
                override fun onBind(
                    holderBase: BaseViewHolder<ItemActionBinding>,
                    model: ActionItemModel,
                    position: Int
                ) {
                    holderBase.binding.model = model
                    holderBase.binding.executePendingBindings()
                }
            },
            itemListener = object : ItemListener<ActionItemModel> {
                override fun onClick(value: ActionItemModel) {
                    findNavController().navigate(
                        StackOverFlowFragmentDirections.actionStackOverFlowFragmentToStackOverFlowDetailFragment(
                            value
                        )
                    )
                }

                override fun onLongClick(value: ActionItemModel) {
                    //("Not yet implemented")
                }
            }
        )
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setupViewModel(viewModel)
        getBinding().adapter = adapter.withLoadStateFooter(LoadStateAdapter { adapter.retry() })
        addPagingAdapterLoadStateListener(getViewModel(), adapter)
    }

    override fun setupData() {
        super.setupData()
        observe(getViewModel().liveData, ::onStateChanged)
    }

    override fun setupListener() {
        getBinding().rootFragmentStackOverFlow.setOnRefreshListener {
            getBinding().rootFragmentStackOverFlow.isRefreshing = false
            adapter.refresh()
        }
    }

    override fun onDestroyView() {
        removePagingAdapterLoadStateListener(getViewModel(), adapter)
        super.onDestroyView()
    }

    private fun onStateChanged(state: StackOverFlowViewModel.State) {
        when (state) {
            is StackOverFlowViewModel.State.LoadItems -> {
                lifecycleScope.launch { adapter.mySummitData(state.items) }
            }
        }
    }
}