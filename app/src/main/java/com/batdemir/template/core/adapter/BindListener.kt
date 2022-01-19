package com.batdemir.template.core.adapter

import androidx.databinding.ViewDataBinding
import com.batdemir.template.other.RecyclerItem

interface BindListener<T : RecyclerItem, V : ViewDataBinding> {
    fun onBind(holderBase: BaseViewHolder<V>, model: T, position: Int)
}