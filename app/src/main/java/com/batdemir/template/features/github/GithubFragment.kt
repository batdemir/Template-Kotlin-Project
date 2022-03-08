package com.batdemir.template.features.github

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.batdemir.core.adapter.*
import com.batdemir.core.extensions.observe
import com.batdemir.core.view.BaseActionLoadState
import com.batdemir.core.view.BaseFragment
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentGithubBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.models.ui.ActionItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubFragment :
    BaseFragment<FragmentGithubBinding, GithubViewModel>(R.layout.fragment_github), BaseActionLoadState {
    private val viewModel: GithubViewModel by viewModels()
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
        getBinding().adapter = adapter.withLoadStateFooter(LoadStateAdapter { adapter.retry() })
        addPagingAdapterLoadStateListener(getViewModel(), adapter)
    }

    override fun setupData() {
        super.setupData()
        observe(getViewModel().liveData, ::onStateChanged)
    }

    override fun setupListener() {
        getBinding().rootFragmentGithub.setOnRefreshListener {
            getBinding().rootFragmentGithub.isRefreshing = false
            adapter.refresh()
        }
    }

    override fun onDestroyView() {
        removePagingAdapterLoadStateListener(getViewModel(), adapter)
        super.onDestroyView()
    }

    private fun onStateChanged(state: GithubViewModel.State) {
        when (state) {
            is GithubViewModel.State.LoadItems -> {
                lifecycleScope.launch { adapter.mySummitData(state.items) }
            }
        }
    }
}