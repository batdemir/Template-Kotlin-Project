package com.batdemir.template.ui.view.github

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.batdemir.template.R
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.paging.GithubSearchParams
import com.batdemir.template.databinding.FragmentGithubBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BasePagingAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GithubFragment :
    BaseFragment<FragmentGithubBinding, GithubViewModel>(R.layout.fragment_github) {
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

    override fun inject() = (requireActivity() as MainActivity).githubComponent.inject(this)

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
                        GithubSearchParams(
                            perPage = Constant.NETWORK_PAGE_SIZE.toLong()
                        )
                    )
                    .collectLatest {
                        adapter.mySummitData(it)
                    }
            }
    }

    override fun setupListener() {
        setPagingAdapterLoadStateListener(viewModel, adapter)
        getBinding().rootFragmentGithub.setOnRefreshListener {
            getBinding().rootFragmentGithub.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removePagingAdapterLoadStateListener(viewModel, adapter)
    }
}