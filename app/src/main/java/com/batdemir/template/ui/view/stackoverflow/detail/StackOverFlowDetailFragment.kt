package com.batdemir.template.ui.view.stackoverflow.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentStackOverFlowDetailBinding
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class StackOverFlowDetailFragment :
    BaseFragment<FragmentStackOverFlowDetailBinding, StackOverFlowDetailViewModel>(R.layout.fragment_stack_over_flow_detail) {
    private val args: StackOverFlowDetailFragmentArgs by navArgs()

    override fun inject() = (requireActivity() as MainActivity).stackOverFlowComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        getBinding().model = args.model
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}