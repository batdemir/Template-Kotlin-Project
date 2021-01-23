package com.batdemir.template.ui.view.notifications

import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentNotificationsBinding
import com.batdemir.template.ui.base.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import javax.inject.Inject

class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding>(R.layout.fragment_notifications) {
    @Inject
    lateinit var viewModel: NotificationsViewModel

    override fun inject() {
        (requireActivity() as MainActivity).notificationsComponent?.inject(this)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        //("Not yet implemented")
    }

    override fun setupData() {
        viewModel.text.observe(viewLifecycleOwner, {
            binding!!.textNotifications.text = it
        })
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}