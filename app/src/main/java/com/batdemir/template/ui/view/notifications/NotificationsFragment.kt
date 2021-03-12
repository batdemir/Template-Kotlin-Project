package com.batdemir.template.ui.view.notifications

import android.os.Bundle
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentNotificationsBinding
import com.batdemir.template.ui.adapter.ActionAdapter
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>(R.layout.fragment_notifications) {
    private val adapter by lazy {
        ActionAdapter()
    }

    override fun inject() = (requireActivity() as MainActivity).notificationsComponent.inject(this)

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
        binding!!.rootFragmentNotification.setOnRefreshListener {
            viewModel.getModels(true)
            binding!!.rootFragmentNotification.isRefreshing = false
        }
    }
}