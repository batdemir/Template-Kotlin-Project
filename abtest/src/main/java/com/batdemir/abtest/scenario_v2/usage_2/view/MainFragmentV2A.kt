package com.batdemir.abtest.scenario_v2.usage_2.view

import android.os.Bundle
import com.batdemir.abtest.R
import com.batdemir.abtest.databinding.FragmentMainV2ABinding
import com.batdemir.abtest.databinding.ItemFragmentMainV2ABinding
import com.batdemir.abtest.z_other.ActionItemModel
import com.batdemir.abtest.z_other.MainViewModel
import com.batdemir.core.core.adapter.BaseAdapter
import com.batdemir.core.core.adapter.BaseViewHolder
import com.batdemir.core.core.adapter.BindListener
import com.batdemir.core.core.view.BaseFragment

class MainFragmentV2A :
    BaseFragment<FragmentMainV2ABinding, MainViewModel>(R.layout.fragment_main_v2_a) {
    private val adapter by lazy {
        BaseAdapter(
            layoutId = R.layout.item_fragment_main_v2_a,
            bindListener = object : BindListener<ActionItemModel, ItemFragmentMainV2ABinding> {
                override fun onBind(
                    holderBase: BaseViewHolder<ItemFragmentMainV2ABinding>,
                    model: ActionItemModel,
                    position: Int
                ) {
                    holderBase.binding.model = model
                    holderBase.binding.executePendingBindings()
                }
            }
        )
    }
    private val items: MutableList<ActionItemModel> = mutableListOf<ActionItemModel>().apply {
        for (i in 0..10) {
            this.add(
                ActionItemModel(
                    id = i.toLong(),
                    title = "test$i",
                    iconRes = "https://images.unsplash.com/photo-1617854818583-09e7f077a156?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                )
            )
        }
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        getBinding().adapter = adapter
    }

    override fun setupData() {
        adapter.submitList(items.toList())
    }
}