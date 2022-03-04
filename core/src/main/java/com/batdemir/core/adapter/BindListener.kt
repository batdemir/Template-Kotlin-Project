package com.batdemir.core.adapter

import androidx.databinding.ViewDataBinding
import com.batdemir.core.models.RecyclerItem

interface BindListener<T : RecyclerItem, V : ViewDataBinding> {
    fun onBind(holderBase: BaseViewHolder<V>, model: T, position: Int)
}