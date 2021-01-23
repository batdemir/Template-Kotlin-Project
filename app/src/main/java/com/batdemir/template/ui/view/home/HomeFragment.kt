package com.batdemir.template.ui.view.home

import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentHomeBinding
import com.batdemir.template.ui.base.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import javax.inject.Inject

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    @Inject
    lateinit var viewModel: HomeViewModel

    override fun inject() {
        (requireActivity() as MainActivity).homeComponent?.inject(this)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        //("Not yet implemented")
    }

    override fun setupData() {
        viewModel.text.observe(viewLifecycleOwner, {
            binding!!.textHome.text = it
        })
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}