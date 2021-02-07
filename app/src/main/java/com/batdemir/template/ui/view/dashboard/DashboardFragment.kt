package com.batdemir.template.ui.view.dashboard

import android.os.Bundle
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentDashboardBinding
import com.batdemir.template.ui.base.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import javax.inject.Inject

class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {
    @Inject
    lateinit var viewModel: DashboardViewModel

    override fun inject() = (requireActivity() as MainActivity).dashboardComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        //("Not yet implemented")
    }

    override fun setupData() {
        viewModel.text.observe(viewLifecycleOwner, {
            binding!!.textDashboard.text = it
        })
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}