package com.batdemir.abtest.scenario_v1.usage_2.view

import android.os.Bundle
import com.batdemir.abtest.R
import com.batdemir.abtest.databinding.FragmentMainV2BBinding
import com.batdemir.abtest.databinding.ItemFragmentMainV2BBinding
import com.batdemir.abtest.scenario_v1.usage_3.view.MainActivityV3
import com.batdemir.abtest.z_other.ActionItemModel
import com.batdemir.abtest.z_other.MainViewModel
import com.batdemir.core.core.adapter.BaseAdapter
import com.batdemir.core.core.adapter.BaseViewHolder
import com.batdemir.core.core.adapter.BindListener
import com.batdemir.core.core.adapter.ItemListener
import com.batdemir.core.core.view.BaseFragment
import com.batdemir.core.extensions.move
import com.google.android.material.tabs.TabLayoutMediator

class MainFragmentV2B :
    BaseFragment<FragmentMainV2BBinding, MainViewModel>(R.layout.fragment_main_v2_b) {
    private val adapter by lazy {
        BaseAdapter(
            layoutId = R.layout.item_fragment_main_v2_b,
            bindListener = object : BindListener<ActionItemModel, ItemFragmentMainV2BBinding> {
                override fun onBind(
                    holderBase: BaseViewHolder<ItemFragmentMainV2BBinding>,
                    model: ActionItemModel,
                    position: Int
                ) {
                    holderBase.binding.model = model
                    holderBase.binding.executePendingBindings()
                }
            },
            itemListener = object : ItemListener<ActionItemModel> {
                override fun onClick(value: ActionItemModel) {
                    requireActivity().move(MainActivityV3::class.java, isKeepHistory = true)
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
        super.setupDefinition(savedInstanceState)
        getBinding().viewPager.adapter = adapter
    }

    override fun setupData() {
        adapter.submitList(items.toList())
    }

    override fun setupListener() {
        TabLayoutMediator(getBinding().tabLayout, getBinding().viewPager) { _, _ ->
        }.attach()
    }
}