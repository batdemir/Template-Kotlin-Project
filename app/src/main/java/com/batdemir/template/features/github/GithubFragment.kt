package com.batdemir.template.features.github

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.batdemir.core.adapter.BaseAdapter
import com.batdemir.core.adapter.BaseViewHolder
import com.batdemir.core.adapter.BindListener
import com.batdemir.core.adapter.ItemListener
import com.batdemir.core.extensions.dismiss
import com.batdemir.core.extensions.observe
import com.batdemir.core.extensions.show
import com.batdemir.core.view.BaseFragment
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentGithubBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.models.ui.ActionItemModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubFragment :
    BaseFragment<FragmentGithubBinding, GithubViewModel>(R.layout.fragment_github) {
    private val viewModel: GithubViewModel by viewModels()
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
                        GithubFragmentDirections.actionGithubFragmentToGithubDetailFragment(
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
        observe(getViewModel().liveData, ::onStateChanged)
    }

    override fun setupListener() {
        getBinding().swipeRefreshLayout.setOnRefreshListener {
            getBinding().swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onStateChanged(state: GithubViewModel.State) {
        when (state) {
            is GithubViewModel.State.Init -> {
                getBinding().swipeRefreshLayout.show()
                getBinding().viewEmpty.dismiss()
                adapter.submitList(state.items)
            }
            is GithubViewModel.State.Empty -> {
                getBinding().swipeRefreshLayout.dismiss()
                getBinding().viewEmpty.show()
            }
        }
    }
}