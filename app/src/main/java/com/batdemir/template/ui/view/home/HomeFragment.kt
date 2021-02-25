package com.batdemir.template.ui.view.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentHomeBinding
import com.batdemir.template.ui.base.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import javax.inject.Inject

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    @Inject
    lateinit var viewModel: HomeViewModel
    private lateinit var settings: MenuItem

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        settings = menu.add("settings")
        settings.setIcon(R.drawable.ic_black_settings)
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        settings.setOnMenuItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
            true
        }
    }

    override fun inject() = (requireActivity() as MainActivity).homeComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
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