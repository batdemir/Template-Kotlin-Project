package com.batdemir.template.features.stackoverflow.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.batdemir.core.core.view.BaseFragment
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentStackOverFlowDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StackOverFlowDetailFragment :
    BaseFragment<FragmentStackOverFlowDetailBinding, StackOverFlowDetailViewModel>(R.layout.fragment_stack_over_flow_detail) {
    private val viewModel: StackOverFlowDetailViewModel by viewModels()
    private val args: StackOverFlowDetailFragmentArgs by navArgs()
    override fun setupDefinition(savedInstanceState: Bundle?) {
        setupViewModel(viewModel)
        getBinding().model = args.model
    }
}
