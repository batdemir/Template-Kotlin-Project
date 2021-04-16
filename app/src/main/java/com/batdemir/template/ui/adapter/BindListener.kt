package com.batdemir.template.ui.adapter

import androidx.databinding.ViewDataBinding
import com.batdemir.template.data.entities.RecyclerItem

interface BindListener<T: RecyclerItem, V : ViewDataBinding> {
    fun onBind(holderBase: BaseViewHolder<V>, model: T, position: Int)
}