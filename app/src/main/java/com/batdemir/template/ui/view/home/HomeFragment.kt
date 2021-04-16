package com.batdemir.template.ui.view.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.batdemir.template.R
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.databinding.FragmentHomeBinding
import com.batdemir.template.databinding.ItemActionBinding
import com.batdemir.template.ui.adapter.BaseAdapter
import com.batdemir.template.ui.adapter.BaseViewHolder
import com.batdemir.template.ui.adapter.BindListener
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    private lateinit var settings: MenuItem
    private val adapter by lazy {
        BaseAdapter(
            layoutId = R.layout.item_action,
            bindListener = object : BindListener<ActionItemModel, ItemActionBinding> {
                override fun onBind(
                    holderBase: BaseViewHolder<ItemActionBinding>,
                    model: ActionItemModel,
                    position: Int
                ) {
                    holderBase.binding.model = model
                }
            }
        )
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
        getBinding().adapter = adapter
        getBinding().viewModel = viewModel
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
            getBinding().executePendingBindings()
        })
    }

    override fun setupListener() {
        //("Not yet implemented")
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