package com.batdemir.template.ui.view.stackoverflow

import android.os.Bundle
import com.batdemir.template.R
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentStackOverFlowBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BaseAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class StackOverFlowFragment :
    BaseFragment<FragmentStackOverFlowBinding, StackOverFlowViewModel>(R.layout.fragment_stack_over_flow) {
    private val adapter by lazy {
        BaseAdapter(
            layoutId = R.layout.item_action,
            bindListener = object : BindListener<ActionItemModel, ItemActionBinding> {
                override fun onBind(
                    holderBase: BaseViewHolder<ItemActionBinding>,
                    model: ActionItemModel,
                    position: Int
                ) {
                    holderBase.binding.model = model
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
        viewModel.getModels(false)
        viewModel.model.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun setupListener() {
        getBinding().rootFragmentStackOverFlow.setOnRefreshListener {
            viewModel.getModels(true)
            getBinding().rootFragmentStackOverFlow.isRefreshing = false
        }
    }
}