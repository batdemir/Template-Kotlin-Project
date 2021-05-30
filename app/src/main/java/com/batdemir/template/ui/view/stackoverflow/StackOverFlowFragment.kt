package com.batdemir.template.ui.view.stackoverflow

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.batdemir.template.R
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.paging.StackOverFlowSearchParams
import com.batdemir.template.databinding.FragmentStackOverFlowBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BasePagingAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import kotlinx.coroutines.flow.collectLatest
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
            }
        )
    }

    override fun inject() = (requireActivity() as MainActivity).stackOverFlowComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        getBinding().adapter = adapter
    }

    override fun setupData() {
        super.setupData()
        viewLifecycleOwner
            .lifecycleScope
            .launch {
                viewModel
                    .repository
                    .getUsersPaging(
                        StackOverFlowSearchParams(
                            pageSize = Constant.NETWORK_PAGE_SIZE.toLong(),
                        )
                    )
                    .collectLatest {
                        adapter.mySummitData(it)
                    }
            }
    }

    override fun setupListener() {
        setPagingAdapterLoadStateListener(viewModel, adapter)
        getBinding().rootFragmentStackOverFlow.setOnRefreshListener {
            getBinding().rootFragmentStackOverFlow.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removePagingAdapterLoadStateListener(viewModel, adapter)
    }
}