package com.batdemir.core.core.adapter

import com.batdemir.core.models.RecyclerItem

interface ItemListener<T : RecyclerItem> {
    fun onClick(value: T)
    fun onLongClick(value: T) = Unit
}
