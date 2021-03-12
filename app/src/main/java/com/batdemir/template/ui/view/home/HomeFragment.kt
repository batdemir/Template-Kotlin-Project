package com.batdemir.template.ui.view.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.batdemir.template.R
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentHomeBinding
import com.batdemir.template.ui.adapter.ActionAdapter
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity
import com.batdemir.template.ui.view.presentation.SecondaryDisplay
import timber.log.Timber


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    private lateinit var settings: MenuItem
    private val adapter: ActionAdapter by lazy {
        ActionAdapter(object : ActionAdapter.ItemListener {
            override fun onClick(model: ActionItemModel) {
                binding!!.executePendingBindings()
            }
        })
    }

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
        binding!!.adapter = adapter
        binding!!.viewModel = viewModel
    }

    override fun setupData() {
        super.setupData()
        viewModel.getDisplays().observe(viewLifecycleOwner, {
            val list = it?.mapIndexed { index, display ->
                ActionItemModel(
                    index.toLong(),
                    display.name,
                    display.displayId.toString(),
                    null,
                    true,
                    null,
                    false
                )
            }
            adapter.submitList(list)
        })
        viewModel.hasHdmiConnection().observe(viewLifecycleOwner, {
            binding!!.executePendingBindings()
        })
    }

    override fun setupListener() {
        binding!!.cardView.setOnClickListener {
            showSecondaryDisplay(R.drawable.uhd)
        }
        binding!!.cardViewTwo.setOnClickListener {
            showSecondaryDisplay(R.drawable.uhd2)
        }
    }

    private fun showSecondaryDisplay(sourceId: Int) {
        try {
            val secondaryDisplay =
                SecondaryDisplay(
                    requireContext(),
                    viewModel.getPresentationDisplay().value,
                    sourceId
                )
            secondaryDisplay.show()
        } catch (e: Exception) {
            AlertDialog.Builder(requireContext())
                .setMessage(e.message)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.register()
    }

    override fun onPause() {
        super.onPause()
        viewModel.unRegister()
    }
}