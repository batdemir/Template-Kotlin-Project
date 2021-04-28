package com.batdemir.template.ui.view.stackoverflow

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.batdemir.template.R
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentStackOverFlowBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BasePagingAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StackOverFlowFragment :
    BaseFragment<FragmentStackOverFlowBinding, StackOverFlowViewModel>(R.layout.fragment_stack_over_flow) {
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
            diffCallback = object : DiffUtil.ItemCallback<ActionItemModel>() {
                override fun areItemsTheSame(oldItem: ActionItemModel, newItem: ActionItemModel): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: ActionItemModel, newItem: ActionItemModel): Boolean {
                    return oldItem.id == newItem.id
                }
            }
        )
    }

    override fun inject() = (requireActivity() as MainActivity).stackOverFlowComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        getBinding().adapter = adapter
        viewModel.getModels()
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
        setPagingAdapterLoadStateListener(viewModel, adapter)
        getBinding().rootFragmentStackOverFlow.setOnRefreshListener {
            getBinding().rootFragmentStackOverFlow.isRefreshing = false
        }
    }
}