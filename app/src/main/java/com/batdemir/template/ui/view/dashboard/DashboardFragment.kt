package com.batdemir.template.ui.view.dashboard

import android.os.Bundle
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentDashboardBinding
import com.batdemir.template.ui.adapter.ActionAdapter
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class DashboardFragment :
    BaseFragment<FragmentDashboardBinding, DashboardViewModel>(R.layout.fragment_dashboard) {
    private val adapter by lazy {
        ActionAdapter()
    }

    override fun inject() = (requireActivity() as MainActivity).dashboardComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        binding!!.adapter = adapter
    }

    override fun setupData() {
        super.setupData()
        viewModel.getModels(false)
        viewModel.model.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun setupListener() {
        binding!!.rootFragmentDashboard.setOnRefreshListener {
            viewModel.getModels(true)
            binding!!.rootFragmentDashboard.isRefreshing = false
        }
    }
}