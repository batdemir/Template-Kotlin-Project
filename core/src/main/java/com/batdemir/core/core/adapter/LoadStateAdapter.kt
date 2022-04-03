package com.batdemir.core.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.batdemir.core.R
import com.batdemir.core.databinding.ViewProgressBinding

class LoadStateAdapter(
    private val retry: () -> Unit = {}
) : LoadStateAdapter<BaseViewHolder<ViewProgressBinding>>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BaseViewHolder<ViewProgressBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewProgressBinding>(layoutInflater, R.layout.view_progress, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewProgressBinding>, loadState: LoadState) {
        holder.binding.root.isVisible = loadState is LoadState.Loading
    }
}
