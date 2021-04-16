package com.batdemir.template.ui.view.github

import android.os.Bundle
import com.batdemir.template.R
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentGithubBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BaseAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class GithubFragment :
    BaseFragment<FragmentGithubBinding, GithubViewModel>(R.layout.fragment_github) {
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

    override fun inject() = (requireActivity() as MainActivity).githubComponent.inject(this)

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
        getBinding().rootFragmentGithub.setOnRefreshListener {
            viewModel.getModels(true)
            getBinding().rootFragmentGithub.isRefreshing = false
        }
    }
}