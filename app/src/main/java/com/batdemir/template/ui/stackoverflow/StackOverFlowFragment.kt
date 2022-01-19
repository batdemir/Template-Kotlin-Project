package com.batdemir.template.ui.stackoverflow

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.batdemir.template.R
import com.batdemir.template.core.adapter.BaseAdapter
import com.batdemir.template.core.adapter.BaseViewHolder
import com.batdemir.template.core.adapter.BindListener
import com.batdemir.template.core.adapter.ItemListener
import com.batdemir.template.core.view.BaseFragment
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentStackOverFlowBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.utils.dismiss
import com.batdemir.template.utils.observe
import com.batdemir.template.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StackOverFlowFragment :
    BaseFragment<FragmentStackOverFlowBinding, StackOverFlowViewModel>(R.layout.fragment_stack_over_flow) {
    private val viewModel: StackOverFlowViewModel by viewModels()
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
        getBinding().adapter = adapter
    }

    override fun setupData() {
        super.setupData()
        observe(viewModel.liveData, ::onStateChanged)
    }

    override fun setupListener() {
        getBinding().swipeRefreshLayout.setOnRefreshListener {
            getBinding().swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onStateChanged(state: StackOverFlowViewModel.State) {
        when (state) {
            is StackOverFlowViewModel.State.Init -> {
                getBinding().swipeRefreshLayout.show()
                getBinding().viewEmpty.dismiss()
                adapter.submitList(state.items)
            }
            StackOverFlowViewModel.State.Empty -> {
                getBinding().swipeRefreshLayout.dismiss()
                getBinding().viewEmpty.show()
            }
        }
    }
}